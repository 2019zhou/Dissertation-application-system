import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Layout from "@/layout/index.vue";
import NotFound from "@/views/other/not_found.vue";
import AccessDenied from "@/views/other/access_denied.vue";
import { GetRole } from "@/request/api";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/test",
    name: "test",
    component: () => import("@/views/student/test.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login/Login.vue"),
  },
  {
    path: "/forget",
    name: "forget",
    component: () => import("@/views/login/Forget.vue"),
  },
  {
    path: "/student",
    name: "student",
    component: Layout,
    meta: {},
    children: [
      {
        path: "/student/personal_info",
        name: "student_personal_info",
        component: () => import("@/views/student/personal_info.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/my_research",
        name: "student_my_research",
        component: () => import("@/views/student/my_research.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/pre_reviews",
        name: "student_pre_reviews",
        component: () => import("@/views/student/pre_reviews.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/get_reviews",
        name: "student_get_reviews",
        component: () => import("@/views/student/get_reviews.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/apply_application",
        name: "student_apply_application",
        component: () => import("@/views/student/apply_application.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/get_presentation_results",
        name: "student_get_presentation_results",
        component: () => import("@/views/student/get_presentation_results.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/apply_degree",
        name: "student_apply_degree",
        component: () => import("@/views/student/apply_degree.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
      {
        path: "/student/get_apply_degree_results",
        name: "student_get_apply_degree_results",
        component: () => import("@/views/student/get_apply_degree_results.vue"),
        meta: { requiresAuth: true, role: "student" },
      },
    ],
  },
  {
    path: "/admin",
    name: "admin",
    component: Layout,
    meta: {},
    children: [
      {
        path: "/admin/get_paper_test_status",
        name: "admin_get_paper_test_status",
        component: () => import("@/views/admin/get_paper_test_status.vue"),
        meta: { requiresAuth: true, role: "manager" },
      },
      {
        path: "/admin/forward_presentation_results",
        name: "admin_forward_presentation_results",
        component: () =>
          import("@/views/admin/forward_presentation_results.vue"),
        meta: { requiresAuth: true, role: "manager" },
      },
      {
        path: "/admin/forward_degree_application_results",
        name: "admin_forward_degree_application_results",
        component: () =>
          import("@/views/admin/forward_degree_application_results.vue"),
        meta: { requiresAuth: true, role: "manager" },
      },
      {
        path: "/admin/submit_reviews",
        name: "admin_submit_reviews",
        component: () => import("@/views/admin/submit_reviews.vue"),
        meta: { requiresAuth: true, role: "manager" },
      },
    ],
  },
  {
    path: '/',
    redirect: () => {
      const isLoggedIn = JSON.parse(localStorage.getItem("login") || "false");
      if (isLoggedIn == "true") {
        const id = JSON.parse(localStorage.getItem("id") || "-1");
        if (id != "-1") {
          GetRole(id).then((res: any) => {
            if (res.data.role == 'student') {
              return '/student/personal_info';
            } else if (res.data.role == 'manager') {
              return '/admin/get_paper_test_status';
            }
          });
        }
      } else {
        return '/login';
      }
    }
  },
  {
    path: "/access-denied",
    component: AccessDenied,
  },
  {
    path: "/:pathMatch(.*)*", // 匹配其他路由
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 路由守卫
router.beforeEach((to, from, next) => {
  if (to.fullPath == '/login' || to.fullPath == '/forget' || to.fullPath == '/test') next();
  const isLoggedIn = JSON.parse(localStorage.getItem("login") || "false");
  if (to.meta.requiresAuth && !isLoggedIn) {
    // 身份验证失败，重定向到登录页面或其他处理
    next('/login');
  } else {
    const id = localStorage.getItem("id")
    if (id) {
      GetRole(id).then((res: any) => {
        localStorage.setItem("role", res.data.role);
        if (to.meta.role && to.meta.role != res.data.role) {
          next("/access-denied");
        } else {
          next();
        }
      });
    } else {
      next("/login");
    }
  }
});

export default router
