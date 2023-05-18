<template>
    <a-form :form="form" v-if="loading" class="presentation-form">
      <a-row :gutter="24">
        <a-col :span="8">
          <a-form-item label="答辩出席人数">
            {{ form.attendance }}
            <!-- <a-input v-model="form.attendance" disabled /> -->
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="答辩时间">
            {{ form.presentation_time }}
            <!-- <a-date-picker v-model="form.presentation_time" disabled /> -->
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="答辩地点">
            {{ form.presentation_location }}
            <!-- <a-input v-model="form.presentation_location" disabled /> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="同意通过论文答辩票数">
            {{ form.paper_pass}}
            <!-- <a-input v-model="form.paper_pass" disabled /> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="不通过论文答辩票数">
            {{ form.paper_fail }}
            <!-- <a-input v-model="form.paper_fail" disabled /> -->
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="同意授予学位票数">
            {{ form.degree_pass }}
            <!-- <a-input v-model="form.degree_pass" disabled /> -->
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="不同意授予学位票数">
            {{ form.degree_fail }}
            <!-- <a-input v-model="form.degree_fail" disabled /> -->
          </a-form-item>
        </a-col>
      </a-row>


    </a-form>
    <p v-if="!loading">
      <h3> 尚未到达此阶段</h3>
    </p>
  </template>
  
  <script lang="ts">
import { GetPresentationResults } from '@/request/api';

  export default{
    data() {
      return {
        form: {
          attendance: 3,
          presentation_time: '',
          presentation_location: "理科大楼2022",
          paper_pass: 3,
          paper_fail: 0,
          degree_pass: 3,
          degree_fail: 0,
        },
        loading:false,
      };
    },
    mounted() {
        this.getpre();
        this.getDataFromLocalStorage();
    },
    methods: {
      getDataFromLocalStorage() {
        // 从 localStorage 中获取值
        const st = localStorage.getItem('stage');
        // 判断是否存在值并进行相应处理
        if(st && st >= '4'){
          this.loading = true;
        }else{
          this.loading = false;
        }
      },
      getpre(){
        GetPresentationResults('51255902041').then((res: any) => {
          this.form.attendance = res.data.directorNum;
          this.form.presentation_time = res.data.presentationTime;
          this.form.presentation_location = res.data.presentationPlace;
          this.form.paper_pass = res.data.votePassNum;
          this.form.paper_fail = res.data.voteFailNum;
          this.form.degree_pass = res.data.voteAgreeNum;
          this.form.degree_fail = res.data.voteDisagreeNum;
        })
      }
    },
  };
  </script>
  
  <style scoped>
  .presentation-form {
    max-width: 800px;
    margin: 0 auto;
  }
  </style>
  


  mounted() {
    this.fetchPaperTitle();
    this.getDataFromLocalStorage();
},
methods: {
  submitapply(){
    localStorage.setItem("stage", '5')
  },
  fetchPaperTitle() {
    const id = localStorage.getItem("id")
    console.log(id)
    if(id){
        GetPaperTitle(id).then((res: any) => {
        this.paperTitle = res.data.PaperTitle
        console.log(this.paperTitle)
      })
    }
  },