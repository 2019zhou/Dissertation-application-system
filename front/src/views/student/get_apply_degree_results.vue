<template>
  <p v-if="isQualified && loading">
    <a-result status="success" title="获得硕士学位"> </a-result>
  </p>
  <p v-if="!isQualified && loading">
    <a-result title="未能获得硕士学位"> </a-result>
  </p>
  <div class="container1">
    <h3>下载学位申请文档 </h3>
  </div>
  <div class="container">
    <a v-if="loading" :href="downloadUrl1" download>
      &nbsp;文档1 &nbsp;<a-button type="primary" shape="round">
        <template #icon>
          <DownloadOutlined />
          <!-- Download -->
        </template>
      </a-button>
    </a>
    <a v-if="loading" :href="downloadUrl2" download>
      &nbsp; 文档2 &nbsp;<a-button type="primary" shape="round">
        <template #icon>
          <DownloadOutlined />
          
          <!-- Download -->
        </template>
      </a-button>
    </a>
  </div>
  <p v-if="!loading">
  <h3> 尚未到达此阶段</h3>
  </p>
</template>
  
<script lang="ts">
import { GetStatus } from '@/request/api';
import { DownloadOutlined } from '@ant-design/icons-vue';

const id = localStorage.getItem("id")

export default {

  data() {
    return {
      isQualified: 1,
      loading: true,
      downloadUrl1: '',
      downloadUrl2: ''
    };

  },
  mounted() {
    this.getDataFromLocalStorage();
    this.fetchDownloadUrl();
  },
  methods: {
    getDataFromLocalStorage() {
      // 从 localStorage 中获取值
      if (id) {
        GetStatus(id).then((res: any) => {
          console.log(res.data.status)
          if (res.message == 'success') {
            if (res.data.status != '7' && res.data.status != '8') {
              this.loading = false;
            } else {
              this.loading = true;
            }
            if (res.data.status == '7') {
              this.isQualified = 0;
            } else if (res.data.status == '8') {
              this.isQualified = 1;
            }
          } else {
            console.log('fail to get the status')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }
    },
    fetchDownloadUrl() {
      this.downloadUrl1 = "http://1.15.174.76:8080/api/GetDegreePdf?student_id=" + id;
      this.downloadUrl2 = "http://1.15.174.76:8080/api/GetPaperPdf?student_id=" + id;
    }
  },
  components: {
    DownloadOutlined,
  },
};
</script>
  

<style>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: -20vh;
}

.container1 {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 8vh;
}
</style>