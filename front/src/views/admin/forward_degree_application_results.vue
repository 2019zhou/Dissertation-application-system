<template>
    <a-table :columns="columns" :data-source="data">
      <template #headerCell="{ column }">
        <template v-if="column.key === 'name'">
            学生姓名
        </template>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'name'">
          <a>
            {{ record.name }}
          </a>
        </template>
        <template v-else-if="column.key === 'tags'">
          <span>
            <a-tag
              v-for="tag in record.tags"
              :key="tag"
              :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
            >
              {{ tag.toUpperCase() }}
            </a-tag>
          </span>
        </template>
        <template v-else-if="column.key === 'action'">
          <span>
            <a>更改</a>
            <a-divider type="vertical" />
            <a>通知学生</a>
            <a-divider type="vertical" />
          </span>
        </template>
      </template>
    </a-table>
  </template>
  <script lang="ts">
  import { SmileOutlined, DownOutlined } from '@ant-design/icons-vue';
  import { defineComponent } from 'vue';
  const columns = [
    {
      name: '姓名',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '学号',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '评审决定',
      dataIndex: 'decision',
      key: 'decision',
    },
    {
      title: '操作',
      key: 'action',
    },
  ];
  
  const data = [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ];
  
  export default defineComponent({
    components: {
      SmileOutlined,
      DownOutlined,
    },
    setup() {
      return {
        data,
        columns,
      };
    },
  });
  </script>
  
  