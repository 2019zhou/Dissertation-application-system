<template>
  <div>
    <a-upload :before-upload="handleUpload" :show-upload-list="false" accept=".pdf">
      <a-button type="primary">上传PDF</a-button>
    </a-upload>
  </div>
</template>

<script>
import axios from 'axios';
import { defineComponent } from 'vue';
import { Upload, Button, message } from 'ant-design-vue';

export default defineComponent({
  components: {
    'a-upload': Upload,
    'a-button': Button,
  },
  methods: {
    handleUpload(file) {
      const formData = new FormData();
      formData.append('user_id', '51255902041');
      formData.append('degree_pdf', file);

      axios.post('http://1.15.174.76:8080/api/SubmitPDF', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }).then(response => {
          // 处理响应数据
          console.log(response);
          message.success('PDF上传成功');
        })
        .catch(error => {
          // 处理错误
          console.error(error);
          console.log("failed")
          message.error('PDF上传失败');
        });
    },
  },
});
</script>

<style>
/* 可根据需要添加样式 */
</style>
