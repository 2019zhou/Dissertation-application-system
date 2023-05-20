<template>
  <div>
    <menu-logo></menu-logo>
    <a-menu
      v-model:openKeys="menuState.openKeys"
      v-model:selectedKeys="menuState.selectedKeys"
      mode="inline"
      theme="dark"
      @openChange="onOpenChange"
    >
      <a-sub-menu key="student" v-if="role == 'student'">
        <template #icon>
          <form-outlined />
        </template>
        <template #title>毕业流程</template>
        <a-menu-item key="personal_info">
          <router-link to="/student/personal_info">个人信息</router-link>
        </a-menu-item>
        <a-menu-item key="my_research">
          <router-link to="/student/my_research">科研成果</router-link>
        </a-menu-item>
        <a-menu-item key="pre_reviews">
          <router-link to="/student/pre_reviews">论文预审</router-link>
        </a-menu-item>
        <a-menu-item key="get_reviews">
          <router-link to="/student/get_reviews">论文盲审结果</router-link>
        </a-menu-item>
        <a-menu-item key="apply_presentation">
          <router-link to="/student/apply_application">答辩申请</router-link>
        </a-menu-item>
        <a-menu-item key="get_presentation_results">
          <router-link to="/student/get_presentation_results"
            >答辩结果</router-link
          >
        </a-menu-item>
        <a-menu-item key="apply_degree">
          <router-link to="/student/apply_degree">学位申请</router-link>
        </a-menu-item>
        <a-menu-item key="get_apply_degree_results">
          <router-link to="/student/get_apply_degree_results"
            >学位申请结果</router-link
          >
        </a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="admin" v-if="role == 'manager'">
        <template #icon>
          <file-search-outlined />
        </template>
        <template #title>流程管理</template>
        <a-menu-item key="get_paper_test_status">
          <router-link to="/admin/get_paper_test_status"
            >论文查重检测</router-link
          >
        </a-menu-item>
        <a-menu-item key="submit_reviews">
          <router-link to="/admin/submit_reviews"
            >盲审意见提交</router-link
          >
        </a-menu-item>
        <a-menu-item key="forward_presentation_results">
          <router-link to="/admin/forward_presentation_results"
            >答辩结果审核</router-link
          >
        </a-menu-item>
        <a-menu-item key="forward_degree_application_results">
          <router-link to="/admin/forward_degree_application_results"
            >学位申请审核</router-link
          >
        </a-menu-item>
      </a-sub-menu>
    </a-menu>
  </div>
</template>
<script lang="ts" setup>
import { reactive } from "vue";
import MenuLogo from "./MenuLogo.vue";
import {
  FormOutlined,
  TeamOutlined,
  FileSearchOutlined,
} from "@ant-design/icons-vue";
import { useRoute } from "vue-router";

// 获取用户信息
const role = localStorage.getItem("role")

// 获取路由
const router = useRoute();

// 定义路由对应的key
var routeMap: { [key: string]: [string, string] } = {
  "/student/personal_info": ["student", "student_personal_info"],
  "/student/my_research": ["student", "student_my_research"],
  "/student/pre_reviews": ["student", "student_pre_reviews"],
  "/student/get_reviews": ["student", "student_get_reviews"],
  "/student/apply_application": ["student", "student_apply_application"],
  "/student/get_presentation_results": [
    "student",
    "student_get_presentation_results",
  ],
  "/student/apply_degree": ["student", "apply_degree"],
  "/student/get_apply_degree_results": ["student", "get_apply_degree_results"],
  "/admin/forward_degree_application": ["admin", "forward_degree_application"],
  "/admin/forward_presentation_results": [
    "admin",
    "forward_presentation_results",
  ],
  "/admin/get_paper_test_status": ["admin", "get_paper_test_status"],
  "/admin/submit_reviews": ["admin", "submit_reviews"],
};

// 定义menu的状态
const menuState = reactive({
  rootSubmenuKeys: ["student", "admin"],
  selectedKeys: [routeMap[router.path][1]],
  openKeys: [routeMap[router.path][0]],
});

// 每次只能打开一个菜单
const onOpenChange = (openKeys: string[]) => {
  if (openKeys.length >= 2) {
    menuState.openKeys = [openKeys[openKeys.length - 1]];
  }
};
</script>