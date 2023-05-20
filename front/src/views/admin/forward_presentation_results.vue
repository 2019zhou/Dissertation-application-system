<template>
  <a-table bordered :data-source="dataSource" :columns="columns">
    <template #bodyCell="{ column, text, record }">
      <template v-if="column.dataIndex === 'num'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].num" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'presentation_time'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].presentation_time" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'presentation_location'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].presentation_location" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'paper_pass'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].paper_pass" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'paper_fail'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].paper_fail" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'degree_pass'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].degree_pass" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'degree_fail'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].degree_fail" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <a @click="edit(record.key)">编辑 | </a>
        <a-popconfirm v-if="dataSource.length" title="确认删除?" @confirm="onDelete(record.key)">
          <a>删除</a>
        </a-popconfirm>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts">
import { computed, defineComponent, reactive, ref } from 'vue';
import type { Ref, UnwrapRef } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import { UpdatePresentationResults } from '@/request/api'


interface DataItem {
  key: string;
  name: string;
  id: string;
  num: string;
  presentation_time: string;
  presentation_location: string;
  paper_pass: string;
  paper_fail: string;
  degree_pass: string;
  degree_fail: string;
}

export default defineComponent({
  components: {
    CheckOutlined,
    EditOutlined,
  },
  setup() {
    const columns = [
      {
        name: '姓名',
        dataIndex: 'name',
      },
      {
        title: '学号',
        dataIndex: 'id',
      },
      {
        title: '答辩出席人数',
        dataIndex: 'num',
      },
      {
        title: '答辩时间',
        dataIndex: 'presentation_time',
      },
      {
        title: '答辩地点',
        dataIndex: 'presentation_location',
      },
      {
        title: '同意通过论文答辩票数',
        dataIndex: 'paper_pass',
      },
      {
        title: '不通过论文答辩票数',
        dataIndex: 'paper_fail',
      },
      {
        title: '同意授予学位票数',
        dataIndex: 'degree_pass',
      },
      {
        title: '同意授予学位票数',
        dataIndex: 'degree_fail',
      },
      {
        title: '操作',
        dataIndex: 'operation',
      },
    ];
    const dataSource: Ref<DataItem[]> = ref([
      {
        key: '0',
        name: 'tangtang',
        id: '51255902041',
        num: '3',
        presentation_time: '16号',
        presentation_location: '大礼堂',
        paper_pass: '3',
        paper_fail: '0',
        degree_pass: '1',
        degree_fail: '1',
      },
    ]);
    const count = computed(() => dataSource.value.length + 1);
    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);
    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);

      const id = JSON.parse(localStorage.getItem('id') || '-1')
      if (id != '-1') {
        UpdatePresentationResults('51255902041', editableData[key].num, editableData[key].presentation_time, editableData[key].presentation_location, editableData[key].paper_pass, editableData[key].paper_fail, editableData[key].degree_pass, editableData[key].degree_fail).then((res: any) => {
          if (res.message == "success") {
            console.log(res.data)
          }
        })
      }
      delete editableData[key];
    };

    const onDelete = (key: string) => {
      dataSource.value = dataSource.value.filter(item => item.key !== key);
    };
    return {
      columns,
      onDelete,
      dataSource,
      editableData,
      count,
      edit,
      save,
    };
  },
});
</script>
<style lang="less">
.editable-cell {
  position: relative;

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    margin-top: 4px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>