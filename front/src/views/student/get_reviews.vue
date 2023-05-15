<template>
  <a-row>
    <a-col :span="24">
      <a-table :columns="columns" :dataSource="data" :pagination="false" />
    </a-col>
  </a-row>
  <p v-if="isQualified">
    <a-result status="success" title="成功通过评审,请进入答辩申请"> </a-result>
  </p>
  <p v-else>
    <a-result title="没有通过评审"> </a-result>
  </p>
</template>



<script lang="ts">
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
          dataIndex: "total",
          key: "total",
        },
      ],
      data: [
        {
          key: "1",
          reviewer: "1",
          indicator1: "90",
          indicator2: "80",
          indicator3: "90",
          indicator4: "80",
          total: "85",
        },
        {
          key: "2",
          reviewer: "2",
          indicator1: "85",
          indicator2: "75",
          indicator3: "90",
          indicator4: "80",
          total: "80",
        },
        {
          key: "3",
          reviewer: "3",
          indicator1: "92",
          indicator2: "78",
          indicator3: "90",
          indicator4: "80",
          total: "85",
        },
      ],
      isQualified: "参加答辩",
    };
  },
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
};
</script>
  