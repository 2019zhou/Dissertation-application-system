<template>
  <a-form :form="form" v-if="loading" @submit="handleSubmit">
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="论文题目" prop="name">
          <a-input v-model:value="form.title" placeholder="请输入论文题目"></a-input>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="关键字" prop="studentId">
          <a-input v-model:value="form.keyWord" placeholder="请输入论文关键字"></a-input>
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="导师工作单位" prop="faculty">
          <a-input v-model:value="form.directorWorkplace" placeholder="请输入导师工作单位"></a-input>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="导师从事的学科领域" prop="department">
          <a-input v-model:value="form.directorDirection" placeholder="请输入导师从事的学科领域"></a-input>
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item label="论文答辩日期">
      <a-input v-model:value="form.presentationTime" />
    </a-form-item>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师一&nbsp;: 姓名" :colon="false">
          <a-input v-model:value="form.director1Name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model:value="form.director1Workplace" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model:value="form.director1Title" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师二&nbsp;: 姓名" :colon="false">
          <a-input v-model:value="form.director2Name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model:value="form.director2Workplace" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model:value="form.director2Title" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="16" class="form-row">
      <a-col :span="8">
        <a-form-item label="导师三&nbsp;: 姓名" :colon="false">
          <a-input v-model:value="form.director3Name" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="单位" :colon="false">
          <a-input v-model:value="form.director3Workplace" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="职称" :colon="false">
          <a-input v-model:value="form.director3Title" />
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
import { GetStatus, UpdateStatus, UpdatePresentationApplication, GetPresentationApplication } from "@/request/api"

const id = localStorage.getItem("id");

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
        title: "",
        keyWord: "",
        presentationTime: "",
        directorWorkplace: "",
        directorDirection: "",
        director1Name: "",
        director1Workplace: "",
        director1Title: "",
        director2Name: "",
        director2Workplace: "",
        director2Title: "",
        director3Name: "",
        director3Workplace: "",
        director3Title: "",
      },
      loading: false
    };
  },
  mounted() {
    this.setloading();
    this.getdata();
  },
  methods: {
    handleSubmit() {
      if (id) {
        UpdateStatus(id, '3').then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully set the stage to 1')
          } else {
            console.log('fail to set the status 1')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }
      console.log("提交的表单数据：", this.form);
      if (id) {
        UpdatePresentationApplication(id, this.form.title, this.form.keyWord, this.form.presentationTime, this.form.directorWorkplace, this.form.director1Name, this.form.director1Workplace, this.form.director1Title, this.form.director2Name, this.form.director2Workplace, this.form.director2Title, this.form.director3Name, this.form.director3Workplace, this.form.director3Title).then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully update the presentation application')
          } else {
            console.log('fail to update the presentation application')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }

    },
    setloading() {
      // 从 localStorage 中获取值
      if (id) {
        GetStatus(id).then((res: any) => {
          if (res.message == 'success') {
            if (res.data.status && res.data.status >= '2') {
              this.loading = true;
            } else {
              this.loading = false;
            }
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }
    },
    getdata() {
      if (id) {
        GetPresentationApplication(id).then((res: any) => {
          this.form = res.data;
        }).catch((err: any) => {
          console.log(err);
        })
      }

    }
  },
};
</script>
  