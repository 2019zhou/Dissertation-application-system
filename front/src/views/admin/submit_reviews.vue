<template>
  <a-table :columns="columns" :data-source="dataSource" bordered>
    <template #bodyCell="{ column, text, record }">
      <template v-if="['name', 'student_id', 'indicator1', 'indicator2', 'indicator3', 'indicator4', 'overallscore'].includes(column.dataIndex)">
        <div>
          <a-input
            v-if="editableData[record.key]"
            v-model:value="editableData[record.key][column.dataIndex]"
            style="margin: -5px 0"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <div class="editable-row-operations">
          <span v-if="editableData[record.key]">
            <a-typography-link @click="save(record.key)"
              >Save</a-typography-link
            >
            <a-popconfirm title="Sure to cancel?" @confirm="cancel(record.key)">
              <a>Cancel</a>
            </a-popconfirm>
          </span>
          <span v-else>
            <a @click="edit(record.key)">更改</a>
          </span>
        </div>
      </template>
    </template>
  </a-table>
</template>
  <script lang="ts">
import { cloneDeep } from "lodash-es";
import { defineComponent, reactive, ref } from "vue";
import type { UnwrapRef } from "vue";

const columns = [
  {
    title: "姓名",
    dataIndex: "name",
    width: "13%",
  },
  {
    title: "学号",
    dataIndex: "student_id",
    width: "13%",
  },
  {
    title: "指标1",
    dataIndex: "indicator1",
    width: "13%",
  },
  {
    title: "指标2",
    dataIndex: "indicator2",
    width: "13%",
  },
  {
    title: "指标3",
    dataIndex: "indicator3",
    width: "13%",
  },
  {
    title: "指标4",
    dataIndex: "indicator4",
    width: "13%",
  },
  {
    title: "总评",
    dataIndex: "overallscore",
    width: "13%",
  },
  {
    title: "operation",
    dataIndex: "operation",
  },
];
interface DataItem {
  name: string;
  student_id: string;
  indicator1: number;
  indicator2: number;
  indicator3: number;
  indicator4: number;
  overallscore: number;
}
const data: DataItem[] = [];
for (let i = 0; i < 50; i++) {
  data.push({
    name: `小明 ${i}`,
    student_id: `${i}`,
    indicator1: 80,
    indicator2: 80,
    indicator3: 80,
    indicator4: 80,
    overallscore: 80
  });
}
export default defineComponent({
  setup() {
    const dataSource = ref(data);
    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      editableData[key] = cloneDeep(
        dataSource.value.filter((item) => key === item.key)[0]
      );
    };
    const save = (key: string) => {
      Object.assign(
        dataSource.value.filter((item) => key === item.key)[0],
        editableData[key]
      );
      delete editableData[key];
    };
    const cancel = (key: string) => {
      delete editableData[key];
    };
    return {
      dataSource,
      columns,
      editingKey: "",
      editableData,
      edit,
      save,
      cancel,
    };
  },
});
</script>
  <style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
  