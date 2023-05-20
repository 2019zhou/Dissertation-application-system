<template>
  <a-form :model="formState" v-bind="layout" name="nest-messages" @finish="onFinish" :label-col="{ span: 6 }"
    :wrapper-col="{ span: 12 }">
    <a-form-item :name="['user', 'title']" label="论文题目">
      <a-input v-model:value="formState.user.title" />
    </a-form-item>
    <a-form-item :name="['user', 'direction']" label="研究方向">
      <a-input v-model:value="formState.user.direction" />
    </a-form-item>
    <a-form-item :name="['user', 'abstract']" label="论文摘要">
      <a-textarea v-model:value="formState.user.abstract" />
    </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 6 }">
      <a-upload v-model:file-list="fileList" name="file" action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
        :headers="headers" @change="handleChange">
        <a-button>
          <upload-outlined></upload-outlined>
          上传论文预审稿
        </a-button>
      </a-upload>
    </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 6 }">
      <a-button @click="submitPaper" type="primary" html-type="submit">更新论文预审信息</a-button>
    </a-form-item>
  </a-form>
</template>


<script lang="ts">
import axios from 'axios';
import { InboxOutlined, UploadOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import { defineComponent, ref, reactive } from "vue";
import type { UploadChangeParam } from "ant-design-vue";
import { UpdateStatus, GetPreReview } from "@/request/api"

const id = localStorage.getItem("id");

export default defineComponent({
  components: {
    InboxOutlined,
    UploadOutlined,
  },
  mounted() {
    this.getData();
  },
  methods: {
    submitPaper() {
      if (id) {
        UpdateStatus(id, '1').then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully set the stage to 1')
          } else {
            console.log('fail to set the status 1')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }
      const formData = new FormData();
      if (id) {
        formData.append('user_id', id);
        formData.append('title', this.formState.user.title);
        formData.append('abstract', this.formState.user.abstract);
        formData.append('paper_direction', this.formState.user.direction);
        formData.append('paper_pdf', this.fileList[0].originFileObj);
        axios
          .post('http://1.15.174.76:8080/api/UpdatePreReview', formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
          })
          .then(response => {
            // 处理响应数据
            console.log(response);
            message.success('PDF上传成功');
          })
          .catch(error => {
            // 处理错误
            console.error(error);
            message.error('PDF上传失败');
          });
      }
    },
    getData() {
      if (id) {
        GetPreReview(id).then((res: any) => {
          console.log(res);
          this.formState.user.title = res.data.title;
          this.formState.user.direction = res.data.paperDirection;
          this.formState.user.abstract = res.data.abstractText;
        }).catch((err: any) => {
          console.log(err);
        })
      }

    }
  },
  setup() {
    const layout = {
      labelCol: { span: 8 },
      wrapperCol: { span: 16 },
    };

    const formState = reactive({
      user: {
        title: "",
        direction: "",
        abstract: "",
      },
    });
    const onFinish = (values: any) => {
      console.log("Success:", values);
    };
    const handleChange = (info: UploadChangeParam) => {
      if (info.file.status !== "uploading") {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === "done") {
        message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === "error") {
        message.error(`${info.file.name} file upload failed.`);
      }
    };
    const fileList = ref([]);
    return {
      formState,
      onFinish,
      layout,
      fileList,
      headers: {
        authorization: "authorization-text",
      },
      handleChange,
    };
  },
});
</script>

