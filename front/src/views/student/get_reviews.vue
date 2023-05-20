<template>
  <a-row v-if="loading">
    <a-col :span="24">
      <a-table :columns="columns" :dataSource="data" :pagination="false" />
    </a-col>
  </a-row>
  <p v-if="isQualified && loading">
    <a-result status="success" title="成功通过评审,请进入答辩申请"> </a-result>
  </p>
  <p v-if="loading && !isQualified">
    <a-result title="没有通过评审"> </a-result>
  </p>
  <p v-if="!loading">
  <h3> 尚未到达此阶段</h3>
  </p>
</template>


<script lang="ts">
import { GetReviews, GetStatus } from '@/request/api'

const id = JSON.parse(localStorage.getItem("id") || "-1");

export default {
  data() {
    return {
      columns: [
        {
          title: "评审人序号",
          dataIndex: "reviewer",
          key: "reviewer",
        },
        {
          title: "指标1",
          dataIndex: "indicator1",
          key: "indicator1",
        },
        {
          title: "指标2",
          dataIndex: "indicator2",
          key: "indicator2",
        },
        {
          title: "指标3",
          dataIndex: "indicator3",
          key: "indicator3",
        },
        {
          title: "指标4",
          dataIndex: "indicator4",
          key: "indicator4",
        },
        {
          title: "总评",
          dataIndex: "generalComment",
          key: "generalComment",
        },
      ],
      data: [],
      isQualified: true,
      loading: true,
    };
  },
  mounted() {
    this.getStatus();
    this.getData();
  },
  methods: {
    getStatus() {
      // 从 localStorage 中获取值
      const st = localStorage.getItem('stage');
      // 判断是否存在值并进行相应处理
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
    },
    getData() {
      GetReviews(id).then((res: any) => {
        if (res.message == 'success') {
          this.data = res.data.reviewSugs;
          console.log(res.data.reviewSugs)
        }
        var sum = 0;
        for(var i = 0;i < this.data.length;i++){
          sum += parseInt(res.data.reviewSugs[i].generalComment);
        }
        if(sum/this.data.length < 75){
          this.isQualified = false;
        }else{
          this.isQualified = true;
        }
        // console.log(sum)
        // console.log(sum/this.data.length)
      }).catch((err: any) => {
        console.log(err);
      })
    }
  }
}

  //   methods: {
  //     checkScore() {
  //       const totalScore = 80// 根据实际情况计算总分数
  //       if (totalScore > 75) {
  //         this.isQualified = true;
  //       } else {
  //         this.isQualified  = false;
  //       }
  //     },
  //   },
</script>
  


