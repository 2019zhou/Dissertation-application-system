<template>
  <a-form :form="form" v-if="loading" class="presentation-form">
    <a-row :gutter="24">
      <a-col :span="8">
        <a-form-item label="答辩人数">
          <a-input :value="form.attendance" disabled />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="答辩时间">
          <a-input :value="form.presentation_time" disabled />
        </a-form-item>
      </a-col>
      <a-col :span="8">
        <a-form-item label="答辩地点">
          <a-input :value="form.presentation_location" disabled />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="同意通过论文答辩票数">
          <a-input :value="form.paper_pass" disabled />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="不通过论文答辩票数">
          <a-input :value="form.paper_fail" disabled />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row :gutter="24">
      <a-col :span="12">
        <a-form-item label="同意授予学位票数">
          <a-input :value="form.degree_pass" disabled />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="不同意授予学位票数">
          <a-input :value="form.degree_fail" disabled />
        </a-form-item>
      </a-col>
    </a-row>
  </a-form>
  <p v-if="!loading">
  <h3> 尚未到达此阶段</h3>
  </p>
</template>
  
<script lang="ts">
import { GetPresentationResults, GetStatus } from '@/request/api';

const id = localStorage.getItem("id");

export default {
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
      loading: false,
    };
  },
  mounted() {
    this.getpre();
    this.getData();
  },
  methods: {
    getData() {
      // 从 localStorage 中获取值
      if (id) {
        GetStatus(id).then((res: any) => {
          if (res.message == 'success') {
            if (res.data.status && res.data.status >= '4') {
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
    getpre() {
      const id = localStorage.getItem("id");
      if (id) {
        GetPresentationResults(id).then((res: any) => {
          this.form.attendance = res.data.directorNum;
          this.form.presentation_time = res.data.presentationTime;
          this.form.presentation_location = res.data.presentationPlace;
          this.form.paper_pass = res.data.votePassNum;
          this.form.paper_fail = res.data.voteFailNum;
          this.form.degree_pass = res.data.voteAgreeNum;
          this.form.degree_fail = res.data.voteDisagreeNum;
        }).catch((err: any) => {
          console.log(err);
        })
      }
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