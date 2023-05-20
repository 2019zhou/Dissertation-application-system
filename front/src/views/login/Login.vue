<template>
    <div class="main">
        <div class="logo">
            <img src="@/assets/logo.png" />
            <span class="title"> 毕业论文及学位申请系统</span>
        </div>
        <p style="text-align: center;color:gray">East China Normal University 求实创造，为人师表</p>
        <a-form :model="formState" name="normal_login" class="login-form" @finish="onFinish" @finishFailed="onFinishFailed">
            <a-form-item name="username" :rules="[{ required: true, message: '请输入用户名!' }]">
                <a-input v-model:value="formState.username" placeholder="用户名">
                    <template #prefix>
                        <UserOutlined class="site-form-item-icon" />
                    </template>
                </a-input>
            </a-form-item>

            <a-form-item name="password" :rules="[{ required: true, message: '请输入密码!' }, { min: 6, message: '密码最短6位' }]">
                <a-input-password v-model:value="formState.password" placeholder="密码">
                    <template #prefix>
                        <LockOutlined class="site-form-item-icon" />
                    </template>
                </a-input-password>
            </a-form-item>

            <!-- <div class="login-form-wrap" >
                    <a-form-item name="remember" style="text-align:left;">
                        <a-checkbox v-model:checked="formState.remember">自动登录</a-checkbox>
                    </a-form-item>
                </div> -->
            <a-form-item>
                <a-button :disabled="disabled" type="primary" html-type="submit" class="login-form-button" block>
                    登录
                </a-button>
            </a-form-item>
            <!-- <div style="float:left;">
                <a href="/register">注册账号</a>
                </div> -->
            <div style="float:left;">
                <a class="login-form-forgot" href="/forget">忘记密码</a>
            </div>
        </a-form>
    </div>
</template>
<script setup lang="ts">
import { createRouterMatcher, useRouter } from 'vue-router';
import { reactive, computed } from 'vue';
import { UserOutlined, LockOutlined, ConsoleSqlOutlined } from '@ant-design/icons-vue';
import { LoginApi, GetRole } from '@/request/api'
import { getBeforeSelectionText } from 'ant-design-vue/lib/vc-mentions/src/util';
import { dataTool } from 'echarts';
interface FormState {
    username: string;
    password: string;
}
const formState = reactive<FormState>({
    username: '',
    password: ''
});
const router = useRouter();

const onFinish = (values: any) => {
    LoginApi(formState.username, formState.password).then((res: any) => {
        console.log(res.message)
        if (res.message == 'success') {
            localStorage.setItem('id', formState.username);
            localStorage.setItem('login', 'true');

            GetRole(formState.username).then((ress: any) => {
                localStorage.setItem('role', ress.data.role)
                console.log(ress.data.role)
                if (ress.data.role == "student") {
                    router.push('/student/personal_info')
                } else if (ress.data.role == 'admin') {
                    router.push('/admin/get_paper_test_status')
                }
            })
        }
    }).catch((err: any) => {
        console.log(err);
    })
};

const onFinishFailed = (errorInfo: any) => {
    console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
    return !(formState.username && formState.password);
});
</script>
<style scoped lang='scss'>
.main {
    text-align: center;
    background-color: #fff;
    border-radius: 20px;
    width: 350px;
    height: 400px;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
}


.logo {
    height: 70px;
    border: none;
    line-height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
    padding-left: 0px;
    color: #ffffff;

    img {
        width: 40px;
        height: 40px;
        margin-left: -10px;
    }

    .title {
        height: 50px;
        text-align: center;
        color: #000000;
        box-sizing: border-box;
        font-weight: 350;
        line-height: 50px;
        font-size: 22px;
        vertical-align: middle;
    }
}
</style>
