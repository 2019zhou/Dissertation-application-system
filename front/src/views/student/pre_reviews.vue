<template>
  <a-form
    :model="formState"
    v-bind="layout"
    name="nest-messages"
    @finish="onFinish"
    :label-col="{ span: 6 }"
    :wrapper-col="{ span: 12 }"
  >
    <a-form-item :name="['user', 'name']" label="论文题目">
      <a-input v-model:value="formState.user.name" />
    </a-form-item>
    <a-form-item :name="['user', 'website']" label="研究方向">
      <a-input v-model:value="formState.user.website" />
    </a-form-item>
    <a-form-item :name="['user', 'introduction']" label="论文摘要">
      <a-textarea v-model:value="formState.user.introduction" />
    </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 6}">
    <a-upload
      v-model:file-list="fileList"
      name="file"
      action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
      :headers="headers"
      @change="handleChange"
    >
      <a-button>
        <upload-outlined></upload-outlined>
        上传论文预审稿
      </a-button>
    </a-upload>
  </a-form-item>
    <a-form-item :wrapper-col="{ ...layout.wrapperCol, offset: 6}">
      <a-button @click="submitPaper" type="primary" html-type="submit">更新论文预审信息</a-button>
    </a-form-item>
  </a-form>
</template>


<script lang="ts">
import { InboxOutlined, UploadOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import { defineComponent, ref, reactive } from "vue";
import type { UploadChangeParam } from "ant-design-vue";
import { SubmitPaperApi } from "@/request/api";

export default defineComponent({
  components: {
    InboxOutlined,
    UploadOutlined,
  },
  methods: {
    submitPaper(){
      localStorage.setItem("stage", '1')
      console.log(localStorage.getItem("stage"))
    }
  },
  setup() {
    const layout = {
      labelCol: { span: 8 },
      wrapperCol: { span: 16 },
    };

    const formState = reactive({
      user: {
        name: "",
        age: undefined,
        email: "",
        website: "",
        introduction: "",
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

