<template>
    <a-form :form="form" class="presentation-form">
      <a-row :gutter="24">
        <a-col :span="8">
          <a-form-item label="答辩出席人数">
            <a-input v-model="form.attendance" disabled />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="答辩时间">
            <a-date-picker v-model="form.presentation_time" disabled />
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="答辩地点">
            <a-input v-model="form.presentation_location" disabled />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="同意通过论文答辩票数">
            <a-input v-model="form.paper_pass" disabled />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="不通过论文答辩票数">
            <a-input v-model="form.paper_fail" disabled />
          </a-form-item>
        </a-col>
      </a-row>
      <a-row :gutter="24">
        <a-col :span="12">
          <a-form-item label="同意授予学位票数">
            <a-input v-model="form.degree_pass" disabled />
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label="不同意授予学位票数">
            <a-input v-model="form.degree_fail" disabled />
          </a-form-item>
        </a-col>
      </a-row>


    </a-form>
  </template>
  
  <script>

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
      };
    },
  };
  </script>
  
  <style scoped>
  .presentation-form {
    max-width: 800px;
    margin: 0 auto;
  }
  </style>
  