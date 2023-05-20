import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Layout from "@/layout/index.vue";
import NotFound from "@/views/other/not_found.vue";
import AccessDenied from "@/views/other/access_denied.vue"
import { GetRole } from "@/request/api";
import { userStore } from "@/store/user";

const routes: Array<RouteRecordRaw> = [
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
        meta: { requiresAuth: true, role: "admin" },
      },
      {
        path: "/admin/forward_presentation_results",
        name: "admin_forward_presentation_results",
        component: () =>
          import("@/views/admin/forward_presentation_results.vue"),
        meta: { requiresAuth: true, role: "admin" },
      },
      {
        path: "/admin/forward_degree_application_results",
        name: "admin_forward_degree_application_results",
        component: () =>
          import("@/views/admin/forward_degree_application_results.vue"),
        meta: { requiresAuth: true, role: "admin" },
      },
      {
        path: "/admin/submit_reviews",
        name: "admin_submit_reviews",
        component: () => import("@/views/admin/submit_reviews.vue"),
        meta: { requiresAuth: true, role: "admin" },
      },
    ],
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
    const isLoggedIn = JSON.parse(localStorage.getItem('login') || "false") 
    const id = JSON.parse(localStorage.getItem("id") || "-1");
    if (to.meta.requiresAuth && !isLoggedIn) {
      // 身份验证失败，重定向到登录页面或其他处理
      next('/login');
    } else{
        if(id){
            GetRole(id).then((res: any) =>{
                localStorage.setItem('role', res.data.role)
                if(to.meta.role && to.meta.role != res.data.role){
                    next('/access-denied');
                }else{
                    next()
                }
            })
        }else{
            next('/login')
        }
    }
  });



// 路由守卫
// router.beforeEach((to, from, next) => {
//   const store = userStore();
//   const id = JSON.parse(localStorage.getItem("id") || "-1");
//   const new_login = localStorage.getItem("new_login") !== null;
//   if (new_login) {
//     localStorage.removeItem("new_login");
//   }
//   if (to.fullPath == "/login" || to.fullPath == "/forget") next();
//   else if (id != "-1") {
//     console.log(store.getState.role)
//     if (store.getState.role == "student") {
//       router.addRoute({
//         path: "/student",
//         name: "student",
//         component: Layout,
//         meta: {},
//         children: [
//           {
//             path: "/student/personal_info",
//             name: "student_personal_info",
//             component: () => import("@/views/student/personal_info.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/my_research",
//             name: "student_my_research",
//             component: () => import("@/views/student/my_research.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/pre_reviews",
//             name: "student_pre_reviews",
//             component: () => import("@/views/student/pre_reviews.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/get_reviews",
//             name: "student_get_reviews",
//             component: () => import("@/views/student/get_reviews.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/apply_application",
//             name: "student_apply_application",
//             component: () => import("@/views/student/apply_application.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/get_presentation_results",
//             name: "student_get_presentation_results",
//             component: () =>
//               import("@/views/student/get_presentation_results.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/apply_degree",
//             name: "student_apply_degree",
//             component: () => import("@/views/student/apply_degree.vue"),
//             meta: {},
//           },
//           {
//             path: "/student/get_apply_degree_results",
//             name: "student_get_apply_degree_results",
//             component: () =>
//               import("@/views/student/get_apply_degree_results.vue"),
//             meta: {},
//           },
//         ],
//       },
//       );
//       router.addRoute({
//         path: "/",
//         name: "Home",
//       });
//       router.addRoute({
//         path: "/",
//         name: "Home",
//         redirect: "/student/personal_info",
//       });
//       next(to.fullPath);
//     }
//   } else next("/login");
// });     redirect: "/student/personal_info",
//       });
//       to.fullPath = "/student/personal_info";
//       next(to.fullPath);
//     } else if (store.getState.role == "manager") {
//       to.fullPath = "/admin/get_paper_test_status";
//       router.addRoute({
//         path: "/admin",
//         name: "admin",
//         component: Layout,
//         meta: {},
//         children: [
//           {
//             path: "/admin/get_paper_test_status",
//             name: "admin_get_paper_test_status",
//             component: () => import("@/views/admin/get_paper_test_status.vue"),
//             meta: {},
//           },
//           {
//             path: "/admin/forward_presentation_results",
//             name: "admin_forward_presentation_results",
//             component: () =>
//               import("@/views/admin/forward_presentation_results.vue"),
//             meta: {},
//           },
//           {
//             path: "/admin/forward_degree_application_results",
//             name: "admin_forward_degree_application_results",
//             component: () =>
//               import("@/views/admin/forward_degree_application_results.vue"),
//             meta: {},
//           },
//           {
//             path: "/admin/submit_reviews",
//             name: "admin_submit_reviews",
//             component: () => import("@/views/admin/submit_reviews.vue"),
//             meta: {},
//           },
//         ],
//    

// 路由守卫
// router.beforeEach((to, from, next) => {
//     const store = userStore();
//     const token = localStorage.getItem('token');
//     const new_login = localStorage.getItem('new_login') !== null;
//     if (new_login) {
//         localStorage.removeItem("new_login");
//     }
//     if (to.fullPath == '/login' || to.fullPath == '/register' || to.fullPath == '/forget') next();
//     else if (token) {
//         if (store.isNull) {
//             GetUserByTokenApi(token).then((res: any) => {
//                 if (res.errno == 0) {
//                     const user = res.data['user'];
//                     store.setState(user)
//                     if (user.role == 'researcher') {
//                         router.addRoute({
//                             path: '/',
//                             name: 'Home',
//                             redirect: '/submission/my'
//                         })
//                         if (new_login && (to.fullPath == '/review/info' || to.fullPath == '/chairman/index')) {
//                             to.fullPath = '/submission/my';
//                         }
//                     }
//                     else if (user.role == 'reviewer') {
//                         router.addRoute({
//                             path: '/',
//                             name: 'Home',
//                             redirect: '/review/info'
//                         })
//                         if (new_login && (to.fullPath == '/submission/my' || to.fullPath == '/chairman/index')) {
//                             to.fullPath = '/review/info';
//                         }
//                     }
//                     else {
//                         router.addRoute({
//                             path: '/',
//                             name: 'Home',
//                             redirect: '/chairman/index'
//                         })
//                         if (new_login && (to.fullPath == '/review/info' || to.fullPath == '/submission/my')) {
//                             to.fullPath = '/chairman/index';
//                         }
//                     }
//                     if (user.role == 'researcher' || user.role == 'reviewer') {
//                         router.addRoute(
//                             {
//                                 path: '/submission',
//                                 name: 'submission',
//                                 component: Layout,
//                                 meta: {},
//                                 children: [
//                                     {
//                                         path: '/submission/my',
//                                         name: 'my_submission',
//                                         component: () => import('@/views/submission/my.vue'),
//                                         meta: {}
//                                     },
//                                     {
//                                         path: '/submission/rebuttal',
//                                         name: 'submission/rebuttal',
//                                         component: () => import('@/views/review/review.vue'),
//                                         meta: {}
//                                     },
//                                     {
//                                         path: '/submission/new',
//                                         name: 'submission/new',
//                                         component: () => import('@/views/submission/new.vue'),
//                                         meta: {}
//                                     }
//                                 ]
//                             }
//                         )
//                     }
//                     if (user.role == 'chairman' || user.role == 'reviewer') {
//                         router.addRoute({
//                             path: '/review',
//                             name: 'review',
//                             component: Layout,
//                             meta: {},
//                             children: [
//                                 {
//                                     path: '/review/info',
//                                     name: 'review_info',
//                                     component: () => import('@/views/review/info.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/review/hall',
//                                     name: 'review_hall',
//                                     component: () => import('@/views/review/hall.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/review/log',
//                                     name: 'review_log',
//                                     component: () => import('@/views/review/log.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/review/edit',
//                                     name: 'review_edit',
//                                     component: () => import('@/views/review/edit.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/review/review',
//                                     name: 'review_review',
//                                     component: () => import('@/views/review/review.vue'),
//                                     meta: {}
//                                 }
//                             ]
//                         })
//                     }
//                     if (user.role == 'chairman') {
//                         router.addRoute({
//                             path: '/chairman',
//                             name: 'chairman',
//                             component: Layout,
//                             meta: {},
//                             children: [
//                                 {
//                                     path: '/chairman/index',
//                                     name: 'chairman_index',
//                                     component: () => import('@/views/chairman/index.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/chairman/manage/user',
//                                     name: 'chairman_manage_user',
//                                     component: () => import('@/views/chairman/manage/user.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/chairman/manage/paper',
//                                     name: 'chairman_manage_paper',
//                                     component: () => import('@/views/chairman/manage/paper.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/chairman/manage/expertise',
//                                     name: 'chairman_manage_expertise',
//                                     component: () => import('@/views/chairman/manage/expertise.vue'),
//                                     meta: {}
//                                 },
//                                 {
//                                     path: '/chairman/review',
//                                     name: 'chairman_review',
//                                     component: () => import('@/views/review/review.vue'),
//                                     meta: {}
//                                 }
//                             ]
//                         })
//                     }
//                     next(to.fullPath);
//                 }
//                 else {
//                     next('/login')
//                 }
//             })
//         } else next();
//     }
//     else next('/login');
// })

export default router;
