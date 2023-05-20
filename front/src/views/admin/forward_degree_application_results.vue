
<template>
  <a-table bordered :data-source="dataSource" :columns="columns">
    <template #bodyCell="{ column, text, record }">
      <template v-if="column.dataIndex === 'decision'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].decision" @pressEnter="save(record.key)" />
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
        <a @click="infostu">告知学生</a>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts">
import { computed, defineComponent, reactive, ref } from 'vue';
import type { Ref, UnwrapRef } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import { UpdateStatus } from '@/request/api'

const id = localStorage.getItem("id");

interface DataItem {
  key: string;
  name: string;
  id: string;
  decision: string;
}

export default defineComponent({
  components: {
    CheckOutlined,
    EditOutlined,
  },
  setup() {
    const columns = [
      {
        name: '学生姓名',
        dataIndex: 'name',
      },
      {
        title: '学号',
        dataIndex: 'id',
      },
      {
        title: '评审决定',
        dataIndex: 'decision',
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
        decision: '通过',
      },
    ]);
    const count = computed(() => dataSource.value.length + 1);
    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);
    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
      UpdateStatus(id, '2').then((res: any) => {
        if (res.message == 'success') {
          console.log('successfully set the stage to 1')
        } else {
          console.log('fail to set the status 1')
        }
      })
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
  methods: {
    infostu() {
      localStorage.setItem("stage", '6')
    }
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

