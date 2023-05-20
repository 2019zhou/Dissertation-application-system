<template>
  <a-button class="editable-add-btn" style="margin-bottom: 8px" @click="handleAdd">增加</a-button>
  <a-table bordered :data-source="dataSource" :columns="columns">
    <template #bodyCell="{ column, text, record }">
      <template v-if="column.dataIndex === 'indicator1'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].indicator1" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'indicator2'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].indicator2" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'indicator3'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].indicator3" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'indicator4'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].indicator4" @pressEnter="save(record.key)" />
            <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
          </div>
          <div v-else class="editable-cell-text-wrapper">
            {{ text || ' ' }}
            <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
          </div>
        </div>
      </template>
      <template v-if="column.dataIndex === 'overallscore'">
        <div class="editable-cell">
          <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.key].overallscore" @pressEnter="save(record.key)" />
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
import { GetAll, GetReviews, UpdateStatus } from '@/request/api'

interface DataItem {
  key: string;
  id: string;
  reviewer: string;
  indicator1: string;
  indicator2: string;
  indicator3: string;
  indicator4: string;
  overallscore: string;
}

export default defineComponent({
  components: {
    CheckOutlined,
    EditOutlined,
  },
  mounted() {
    this.getData();
  },
  methods: {
    //http://1.15.174.76:8080/api/GetReviews?student_id=51255902041
    getData() {
      GetAll().then((res: any) => {
        for (var i = 0; i < res.data.length; i++) {
          if (res.data[i].id == '000' || parseInt(res.data.degreeApplicationStatus) < 2) {
            continue;
          } else {
            // console.log(res.data[i].id)
            GetReviews(res.data[i].id).then((res2: any) => {
              // console.log(res2)
              if (res2.message == 'success') {
                for (var j = 0; j < res2.data.reviewSugs.length; j++) {
                  console.log(res2.data.reviewSugs[j])
                  this.dataSource.push({
                    key: (i*10 + j).toString(),
                    id: res2.data.reviewSugs[j].userId,
                    reviewer: res2.data.reviewSugs[j].reviewer,
                    indicator1: res2.data.reviewSugs[j].indicator1,
                    indicator2: res2.data.reviewSugs[j].indicator2,
                    indicator3: res2.data.reviewSugs[j].indicator3,
                    indicator4: res2.data.reviewSugs[j].indicator4,
                    overallscore: res2.data.reviewSugs[j].generalComment,
                  })
                }
              }
            }).catch((err: any) => {
              console.log(err)
            })
          }
        }
      }).catch((err: any) => {
        console.log(err)
      })
    }
  },
  setup() {
    const columns = [
      {
        title: '学生姓名',
        dataIndex: 'id',
      },
      {
        title: '盲审人',
        dataIndex: 'reviewer',
      },
      {
        title: '指标1',
        dataIndex: 'indicator1',
      },
      {
        title: '指标2',
        dataIndex: 'indicator2',
      },
      {
        title: '指标3',
        dataIndex: 'indicator3',
      },
      {
        title: '指标4',
        dataIndex: 'indicator4',
      },
      {
        title: '总分',
        dataIndex: 'overallscore',
      },
      {
        title: '操作',
        dataIndex: 'operation',
      },
    ];
    const dataSource: Ref<DataItem[]> = ref([
    ]);
    const count = computed(() => dataSource.value.length + 1);
    const editableData: UnwrapRef<Record<string, DataItem>> = reactive({});

    const edit = (key: string) => {
      editableData[key] = cloneDeep(dataSource.value.filter(item => key === item.key)[0]);
    };
    const save = (key: string) => {
      Object.assign(dataSource.value.filter(item => key === item.key)[0], editableData[key]);
      localStorage.setItem("stage", '2')
      const id = localStorage.getItem("id")
      if (id) {
        UpdateStatus(id, '2').then((res: any) => {
          if (res.message == 'success') {
            console.log('successfully set the stage to 1')
          } else {
            console.log('fail to set the status 1')
          }
        }).catch((err: any) => {
          console.log(err);
        })
      }

      delete editableData[key];
    };

    const onDelete = (key: string) => {
      dataSource.value = dataSource.value.filter(item => item.key !== key);
    };
    const handleAdd = () => {
      const newData = {
        key: `${count.value}`,
        name: `tangtang`,
        reviewer: `盲审人 ${count.value}`,
        indicator1: `80`,
        indicator2: `80`,
        indicator3: `80`,
        indicator4: `80`,
        overallscore: `90`,
      };
      dataSource.value.push(newData);
    };

    return {
      columns,
      onDelete,
      handleAdd,
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