<template>
  <a-form :model="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 16 }">
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="姓名" prop="name">
          <input :value="form.name" disabled/>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="学号" prop="studentId">
          <input v-model="form.studentId">
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="院系" prop="faculty">
          <input v-model="form.department">
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="学院" prop="department">
          <input v-model="form.college">
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="学制" prop="length_of_schooling">
          <input v-model="form.length_of_schooling">
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="研究方向" prop="research_direction">
          <input v-model="form.research_direction">
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="政治面貌" prop="political_status">
          <input v-model="form.political_status">
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="联系电话" prop="phone">
          <input v-model="form.phone">
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="联系邮箱" prop="mail">
          <input v-model="form.mail">
        </a-form-item>
      </a-col>
    </a-row>
    <a-form-item :wrapper-col="{ offset: 2, span: 12 }">
      <a-button type="primary" @click="updateinfo">提交</a-button>
    </a-form-item>
  </a-form>
</template>
  
<script lang="ts">
import { GetPersonalInfo, UpdatePersonalInfo } from '@/request/api'

const id = JSON.parse(localStorage.getItem("id") || "-1");

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
        phone: '18917304018',
        mail: 'ttt0630@qq.com',
      },
      submitted: false,
    };
  },
  mounted() {
    this.getPersonalInfo();
  },
  methods: {
    getPersonalInfo(){
      if(id != '-1'){
        GetPersonalInfo(id).then((res:any)=>{
          if(res.message == "success"){
            this.form.name = res.data.username
            this.form.studentId = res.data.id
            this.form.department = res.data.department
            this.form.college = res.data.faculty
            this.form.length_of_schooling = res.data.academicSystem
            this.form.research_direction = res.data.researchDirection
            this.form.political_status = res.data.politicalStatus
            this.form.phone = res.data.phone
            this.form.mail = res.data.mail
          }else{
            console.log(res)
          }
        }).catch((err:any)=>{
          console.log(err)
        }) 
      }
    },
    updateinfo() {
      this.submitted = false
      console.log(this.form.name)
      console.log(this.form.studentId)
      UpdatePersonalInfo(this.form.studentId, this.form.name, this.form.department, this.form.college, this.form.length_of_schooling, this.form.research_direction, this.form.political_status, this.form.phone, this.form.mail).then((res:any)=>{
        if(res.message == "success"){
          console.log("更新成功")
        }else{
          console.log(res)
        }
      }).catch((err:any)=>{
        console.log(err)
      })
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

.a-row+.a-row {
  margin-top: 24px;
}

.a-col {
  padding-right: 0;
  padding-left: 0;
}
input{
    outline-style: none ;
    border: 1px solid #ccc; 
    border-radius: 3px;
    padding: 6px;
    width: 300px;
    font-size: 14px;
    font-family: "Microsoft soft";
}
input:focus{
    border-color: #66afe9;
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6)
}
</style>
  