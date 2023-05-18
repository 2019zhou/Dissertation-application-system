<template>
    <p v-if="isQualified && loading">
      <a-result status="success" title="获得硕士学位"> </a-result>
    </p>
    <p v-if="!isQualified && loading">
      <a-result title="未能获得硕士学位"> </a-result>
    </p>
    <p v-if="!loading">
      <h3> 尚未到达此阶段</h3>
    </p>
  </template>
  
  <script lang="ts">
  export default {
    data() {
      return {
        isQualified: 1,
        loading: true
      };
      
    },
    mounted() {
      this.getDataFromLocalStorage();
    },
    methods: {
      getDataFromLocalStorage() {
        // 从 localStorage 中获取值
        const st = localStorage.getItem('stage');
        // 判断是否存在值并进行相应处理
        if(st && st >= '6'){
          this.loading = true;
        }else{
          this.loading = false;
        }
      }
    }
  };
  </script>
    