<template>
  <div>
    <a-card
      v-for="(publication, index) in publications"
      :key="index"
      style="margin-bottom: 26px"
    >
            <a-form-item label="发表论文">
              <a-input
                v-model="publication.paper"
                placeholder="请输入论文名称"
              />
            </a-form-item>
            <a-form-item label="作者位次">
              <a-input
                v-model="publication.journal"
                placeholder="请输入第几作者"
              />
            </a-form-item>
            <a-form-item label="发表地址">
              <a-input
                v-model="publication.journal"
                placeholder="请输入期刊/会议名称"
              />
            </a-form-item>
            <a-form-item label="发表日期">
              <a-date-picker v-model="publication.date" format="YYYY-MM-DD" />
            </a-form-item>
            <a-form-item label="发表类型">
              <a-select v-model="publication.type" placeholder="请选择类型">
                <a-select-option value="CCF-A">CCF-A</a-select-option>
                <a-select-option value="CCF-B">CCF-B</a-select-option>
                <a-select-option value="CCF-C">CCF-C</a-select-option>
                <a-select-option value="一区">SCI &nbsp;一区</a-select-option>
                <a-select-option value="二区">SCI &nbsp;二区</a-select-option>
                <a-select-option value="三区">SCI &nbsp;三区</a-select-option>
                <a-select-option value="三区">EI</a-select-option>
              </a-select>
            </a-form-item>
          <a-button type="danger-default" @click="deletePublication(index)"
            >删除</a-button
          >
    </a-card>
    <a-row>
      <a-col :span="20">
        <a-form-item>
          <a-button type="dashed" @click="addPublication"
            >添加发表论文</a-button
          >
        </a-form-item>
      </a-col>
    </a-row>
  </div>
</template>
  
  <script lang="ts">
export default {
  data() {
    return {
      publications: [{ paper: "", journal: "", date: "", type: "" }],
      labelCol: { span: 4 },
      wrapperCol: { span: 16 },
    };
  },
  methods: {
    addPublication() {
      this.publications.push({ name: "", date: "" });
    },
    deletePublication(index) {
      this.publications.splice(index, 1);
    },
  },
};
</script>
  
  <style scoped>
.delete-button-col {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
  