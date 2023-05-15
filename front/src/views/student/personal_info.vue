<template>
    <a-form :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="姓名" prop="name">
            <a-input v-model="form.name" placeholder="请输入姓名"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学号" prop="studentId">
            <a-input v-model="form.studentId" placeholder="请输入学号"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="院系" prop="faculty">
            <a-input v-model="form.department" placeholder="请输入院系"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学院" prop="department">
            <a-input v-model="form.college" placeholder="请输入学院"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="学制" prop="length_of_schooling">
            <a-input v-model="form.length_of_schooling" placeholder="请输入学制"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="研究方向" prop="research_direction">
            <a-input v-model="form.research_direction" placeholder="请输入研究方向"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="政治面貌" prop="political_status">
            <a-input v-model="form.political_status" placeholder="请输入政治面貌"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="联系电话" prop="phone">
            <a-input v-model="form.phone" placeholder="请输入电话"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="联系邮箱" prop="mail">
            <a-input v-model="form.mail" placeholder="请输入邮箱"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item :wrapper-col="{ offset: 2, span: 12 }">
        <a-button type="primary" @click="handleSubmit">更新个人信息</a-button>
      </a-form-item>
    </a-form>
  </template>
  
<script lang="ts">
  export default {
    data() {
      return {
        form: {
          name: '',
          studentId: '',
          department: '',
          college: '',
          length_of_schooling: '',
          research_direction: '',
          political_status: '',
          phone: '',
          mail:'',
        },
      };
    },
    methods: {
      handleSubmit() {
        // this.$refs.form.validate((valid) => {
        //   if (valid) {
        //     console.log('表单校验通过');
        //     console.log(this.form);
        //     // TODO：提交表单数据
        //   } else {
        //     console.log('表单校验未通过');
        //     return false;
        //   }
        // });
        console.log("log")
      },
    },
  };
  </script>
  
<style scoped>
.a-form-item-label {
text-align: right;
padding-right: 16px;
}
.a-form-item-control-wrapper {
display: inline-block;
width: calc(50% - 60px);
vertical-align: middle;
padding-left: 12px;
padding-right: 12px;
}
.a-row + .a-row {
margin-top: 24px;
}
.a-col {
padding-right: 0;
padding-left: 0;
}
</style>
  