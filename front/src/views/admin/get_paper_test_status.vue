<template>
  <a-table :columns="columns" :data-source="tableData">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'name'">
        <span>
          学生姓名
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'results'">
        <a>
          {{ randomNumber }} %
        </a>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a @click="generateRate">论文重复率检测</a>
          <a-divider type="vertical" />
          <a>告知学生</a>
          <a-divider type="vertical" />
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts">
import { SmileOutlined, DownOutlined, VideoCameraOutlined } from '@ant-design/icons-vue';
import { defineComponent } from 'vue';
import { string } from 'vue-types';
import { GetAllPersentationResults } from "@/request/api"


export default defineComponent({
  components: {
    SmileOutlined,
    DownOutlined,
  },
  data() {
    return {
      columns: [
        {
          name: '姓名',
          dataIndex: 'username',
          key: 'username',
        },
        {
          title: '学号',
          dataIndex: 'userID',
          key: 'userID',
        },
        {
          title: '论文题目',
          dataIndex: 'title',
          key: 'title',
        },
        {
          title: '查重检测',
          key: 'results',
          dataIndex: 'results',
        },
        {
          title: '操作',
          key: 'action',
        },
      ],
      tableData: [],
      randomNumber: ''
    };
  },
  mounted() {
    this.getdata();
  },
  methods: {
    generateRate() {
      this.randomNumber = Math.floor(Math.random() * 15 + 1).toString();
      console.log("dddd");
    },
    getdata() {
      GetAllPersentationResults().then((res: any) => {
        console.log(res);
        this.tableData = res.data;
      }).catch((err: any) => {
        console.log(err);
      })
    }
  },
});
</script>

