<template>
  <a-form :model="formState" v-bind="layout" v-if="loading" name="nest-messages" @finish="onFinish"
    :label-col="{ span: 6 }" :wrapper-col="{ span: 12 }">
    <!-- <a-form-item :name="['user', 'name']" label="论文题目" >
        {{ paperTitle }}
      </a-form-item> -->
    <div class="text-container">
      <a-card class="text-card" :title="paperTitle">
        <!-- <span class="text-content">{{ paperTitle }}</span> -->
      </a-card>
    </div>
    <br>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 9 }">
      <a-upload v-model="fileList" name="file" action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
        :headers="headers" @change="handleChange">
        <a-button>
          <upload-outlined></upload-outlined>
          上传学位申请表
        </a-button>
      </a-upload>
    </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 9 }">
      <a-button @click="submitApply" type="primary" html-type="submit">更新学位申请表</a-button>
    </a-form-item>
  </a-form>
  <p v-if="!loading">
  <h3> 尚未到达此阶段</h3>
  </p>
</template>
  
  
<script lang="ts">
import { InboxOutlined, UploadOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import { defineComponent, ref, reactive } from "vue";
import type { UploadChangeParam } from "ant-design-vue";
import { GetPaperTitle } from '@/request/api';
import { any } from "vue-types";
import { GetStatus, UpdateStatus, SubmitPDF} from "@/request/api"


import axios from 'axios';

export const submitFormData = (formData: FormData) => {
  return axios.post('/api/SubmitPDF', formData, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
};


const id = localStorage.getItem("id")

export default defineComponent({
  data() {
    return {
      fileList: '',
      paperTitle: '',
      loading: true
    };
  },
  components: {
    InboxOutlined,
    UploadOutlined,
  },
  mounted() {
    this.fetchPaperTitle();
    this.setloading();
  },
  methods: {
    submitApply() {
      if (id) {
        UpdateStatus(id, '5').then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully set the stage to 5')
          } else {
            console.log('fail to set the status 5')
          }
        }).catch((err: any) => {
          console.log(err);
        })
        const formData = new FormData();
        formData.append('user_id', id);
        formData.append('degree_pdf', this.fileList);
        SubmitPDF(formData).then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully submit the pdf')
          } else {
            console.log('fail to submit the pdf')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }
    },
    fetchPaperTitle() {
      if (id) {
        GetPaperTitle(id).then((res: any) => {
          this.paperTitle = res.data.PaperTitle
          console.log(this.paperTitle)
        })
      }
    },
    setloading() {
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
    }
  },
  setup() {
    const layout = {
      labelCol: { span: 8 },
      wrapperCol: { span: 16 },
    };

    const formState = reactive({
    });
    const onFinish = (values: any) => {
      console.log("Success:", values);
    };
    const handleChange = (info: UploadChangeParam) => {
      console.log(info)
      if (info.file.status !== "uploading") {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === "done") {
        message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === "error") {
        message.error(`${info.file.name} file upload failed.`);
      }
    };
    return {
      formState,
      onFinish,
      layout,
      headers: {
        authorization: "authorization-text",
      },
      handleChange,
    };
  },

});
</script>
  
<style>
.text-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.text-card {
  width: 300px;
  text-align: center;
}

.text-content {
  font-size: 20px;
  display: inline-block;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>
