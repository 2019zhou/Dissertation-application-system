<template>
    <a-form v-show="!submitted" :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="姓名" prop="name">
            {{ form.name }}
            <!-- <a-input v-model="form.name" placeholder="请输入姓名"></a-input> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学号" prop="studentId">
            {{ form.studentId }}
            <!-- <a-input v-model="form.studentId" placeholder="请输入学号"></a-input> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="院系" prop="faculty">
            {{ form.department }}
            <!-- <a-input v-model="form.department" placeholder="请输入院系"></a-input> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学院" prop="department">
            {{ form.college }}
            <!-- <a-input v-model="form.college" placeholder="请输入学院"></a-input> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="学制" prop="length_of_schooling">
            {{ form.length_of_schooling }}
            <!-- <a-input v-model="form.length_of_schooling" placeholder="请输入学制"></a-input> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="研究方向" prop="research_direction">
            {{ form.research_direction }}
            <!-- <a-input v-model="form.research_direction" placeholder="请输入研究方向"></a-input> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="政治面貌" prop="political_status">
            {{ form.political_status }}
            <!-- <a-input v-model="form.political_status" placeholder="请输入政治面貌"></a-input> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="联系电话" prop="phone">
            {{ form.phone }}
            <!-- <a-input v-model="form.phone" placeholder="请输入电话"></a-input> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="联系邮箱" prop="mail">
            {{ form.mail }}
            <!-- <a-input v-model="form.mail" placeholder="请输入邮箱"></a-input> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item :wrapper-col="{ offset: 2, span: 12 }">
        <a-button type="primary" @click="handleSubmit">更新个人信息</a-button>
      </a-form-item>
    </a-form>
    <a-form v-show="submitted" :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="姓名" prop="name">
            <a-input v-model="form.name"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学号" prop="studentId">
            <a-input v-model="form.studentId"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="院系" prop="faculty">
            <a-input v-model="form.department"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="学院" prop="department">
            <a-input v-model="form.college"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="学制" prop="length_of_schooling">
            <a-input v-model="form.length_of_schooling" ></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="研究方向" prop="research_direction">
            <a-input v-model="form.research_direction" ></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="政治面貌" prop="political_status">
            <a-input v-model="form.political_status"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="联系电话" prop="phone">
            <a-input v-model="form.phone"></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="联系邮箱" prop="mail">
            <a-input v-model="form.mail" placeholder=form.mail></a-input>
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item :wrapper-col="{ offset: 2, span: 12 }">
        <a-button type="primary" @click="updateinfo">提交</a-button>
      </a-form-item>
    </a-form> 
  </template>
  
<script lang="ts">
import { UpdatePersonalInfo} from '@/request/api'
  export default {
    data() {
      return {
        form: {
          name: '汤汤大王',
          studentId: '51255902041',
          department: '软件学院',
          college: '软件学院',
          length_of_schooling: '四年',
          research_direction: '软件工程',
          political_status: '共青团员',
          phone: 'ttt0630@qq.com',
          mail:'ttt0630@qq.com',
        },
        submitted: false,
      };
    },
    methods: {
      handleSubmit() {
        console.log(this.form.studentId)
        this.form.name = "tangtang"
        this.submitted = true
        // UpdatePersonalInfo(this.form.studentId, this.form.name, this.form.department, this.form.college, this.form.length_of_schooling, this.form.research_direction, this.form.political_status, this.form.phone, this.form.mail).then((res:any)=>{
        //   if(res.message == "success"){
        //     console.log("更新成功")
        //   }else{
        //     console.log(res)
        //   }
        // })
      },
      updateinfo(){
        this.submitted = false
      }
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
  