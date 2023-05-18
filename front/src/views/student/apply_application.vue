<template>
  <a-form :form="form" v-if="loading" @submit="handleSubmit">
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="论文题目" prop="name">
          <a-input v-model="form.dissertation_name" placeholder="请输入论文题目"></a-input>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="关键字" prop="studentId">
          <a-input v-model="form.key_word" placeholder="请输入论文关键字"></a-input>
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="导师工作单位" prop="faculty">
          <a-input v-model="form.director_affiliation" placeholder="请输入导师工作单位"></a-input>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="导师从事的学科领域" prop="department">
          <a-input v-model="form.director_direction" placeholder="请输入导师从事的学科领域"></a-input>
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item label="论文答辩日期">
              <a-date-picker v-model="form.presentation_time" format="YYYY-MM-DD" />
            </a-form-item>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师一&nbsp;: 姓名" :colon="false">
          <a-input v-model="form.director1_name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model="form.director1_affiliation" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model="form.director1_title" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师二&nbsp;: 姓名" :colon="false">
          <a-input v-model="form.director2_name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model="form.director2_affiliation" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model="form.director2_title" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师三&nbsp;: 姓名" :colon="false">
          <a-input v-model="form.director3_name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model="form.director3_affiliation" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model="form.director3_title" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item :wrapper-col="{ offset: 0, span: 12 }">
        <a-button type="primary" @click="handleSubmit">更新答辩申请</a-button>
      </a-form-item>
  </a-form>
  <p v-if="!loading">
  <h3> 尚未到达此阶段</h3>
  </p>
</template>
  
  <script lang="ts">
import { Form, Input, Button, Textarea, Row, Col } from "ant-design-vue";

export default {
  components: {
    Form,
    Input,
    Button,
    Textarea,
    Row,
    Col,
  },
  data() {
    return {
      form: {
        dissertation_name: "",
        key_word: "",
        presentation_time: "",
        director_affiliation: "",
        director_direction: "",
        director1_name: "",
        director1_affiliation: "",
        director1_title: "",
        director2_name: "",
        director2_affiliation: "",
        director2_title: "",
        director3_name: "",
        director3_affiliation: "",
        director3_title: "",
      },
      loading: false
    };
  },
  mounted() {
    this.getDataFromLocalStorage();
  },
  methods: {
    handleSubmit() {
      localStorage.setItem('stage', '3')
      console.log("提交的表单数据：", this.form);
    },
    getDataFromLocalStorage() {
      // 从 localStorage 中获取值
      const st = localStorage.getItem('stage');
      // 判断是否存在值并进行相应处理
      if(st && st >= '2'){
        this.loading = true;
      }else{
        this.loading = false;
      }
    }
  },
};
</script>
  