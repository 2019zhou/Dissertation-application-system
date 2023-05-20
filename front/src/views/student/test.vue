<template>
    <div>
      <a-upload
        :action="uploadUrl"
        :before-upload="beforeUpload"
        :show-upload-list="false"
      >
        <a-button type="primary">选择PDF文件</a-button>
      </a-upload>
      <a-form ref="form" :form="form" @finish="handleSubmit">
        <a-form-item name="name" label="文件名">
          <a-input v-model="formData.name" />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit">提交</a-button>
        </a-form-item>
      </a-form>
    </div>
  </template>
  
  <script lang = "ts">
  import axios from 'axios';
  
  export default {
    data() {
      return {
        uploadUrl: 'your_upload_endpoint', // 上传接口的URL
        form: null,
        formData: {
          name: ''
        }
      };
    },
    methods: {
      beforeUpload(file) {
        const formData = new FormData();
        formData.append('file', file);
        // 在这里可以进行其他参数的附加，例如：formData.append('name', this.formData.name);
        
        // 发送上传请求，使用axios发送请求
        axios.post(this.uploadUrl, formData).then(response => {
          // 在上传成功后的回调中可以处理返回的数据或执行其他操作
          console.log('文件上传成功');
        }).catch(error => {
          // 处理上传过程中的错误
          console.error('文件上传失败:', error);
        });
  
        return false; // 阻止默认上传行为
      },
      handleSubmit() {
        this.form.validateFields().then(values => {
          // 在这里可以将表单数据和上传文件一起提交到后端
          const formData = new FormData();
          formData.append('file', values.file);
          // 添加其他表单数据
          formData.append('name', this.formData.name);
          
          // 发送提交请求，使用axios发送请求
          axios.post('your_submit_endpoint', formData).then(response => {
            // 在提交成功后的回调中可以处理返回的数据或执行其他操作
            console.log('提交成功');
          }).catch(error => {
            // 处理提交过程中的错误
            console.error('提交失败:', error);
          });
        });
      }
    },
    mounted() {
      this.form = this.$refs.form.form;
    }
  };
  </script>
  