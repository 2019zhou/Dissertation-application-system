<template>
  <a-table :columns="columns" :data-source="data2">
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
          {{ randomNumber}} %
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
const columns = [
  {
    name: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '学号',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '论文题目',
    dataIndex: 'dissertation_name',
    key: 'dissertation_name',
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
];

const data2 = [
  {
    key: '1',
    name: 'tangtang',
    id: 51255902041,
    dissertation_name: 'aaa',
    results: '',
  },
];

export default defineComponent({
  components: {
    SmileOutlined,
    DownOutlined,
  },
  data() {
    return {
      randomNumber: ''
    };
  },
  methods: {
    generateRate(){
      this.randomNumber = Math.floor(Math.random() * 15 + 1).toString();
      console.log("dddd");
    }
  },
  setup() {
    return {
      data2,
      columns,
    };
  },
});
</script>

