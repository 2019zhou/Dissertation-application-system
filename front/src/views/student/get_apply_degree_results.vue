<template>
    <p v-if="isQualified">
      <a-result status="success" title="获得硕士学位"> </a-result>
    </p>
    <p v-else>
      <a-result title="未能获得硕士学位"> </a-result>
    </p>
  </template>
  
  <script lang="ts">
  export default {
    data() {
      return {
        isQualified: 0,
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
    