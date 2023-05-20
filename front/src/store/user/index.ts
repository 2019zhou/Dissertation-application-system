import { defineStore } from 'pinia'


export const userStore = defineStore('user', {
    state: () => {
        return {
            id: null,
            role: null
        }
    },
    getters: {
        getState(state) {
            return state
        },
        isNull(state) {
            return state.id == null;
        }
    },
    actions: {
        setState(user: any) {
            this.id = user.username
            this.role = user.role
        },
        clear() {
            this.id = null
            this.role = null
        }
    }
})