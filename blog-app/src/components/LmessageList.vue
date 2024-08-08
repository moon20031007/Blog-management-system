<template>
    <div class="lmessage-list">
        <p>已有留言共{{ lmessages.length }}条</p>
        <main>
            <article v-for="lmessage in lmessages" :key="lmessage.lmessageId">
                <div class="lmessage-card">
                    <header>
                        <p class="meta">By 用户{{ lmessage.commenterId }} on {{ formatLeaveTime(lmessage.leaveTime) }}</p>
                    </header>
                    <section>
                        <p>{{ lmessage.content }}</p>
                    </section>
                </div>
            </article>
        </main>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'MyLmessageList',
    data() {
        return {
            lmessages: []
        }
    },
    async created() {
        await this.fetchLmessages();
    },
    methods: {
        async fetchLmessages() {
            axios.get('http://localhost:7000/lmessage/list')
                .then(response => {
                    this.lmessages = response.data.data;
                })
                .catch(error => {
                    console.error('获取留言失败:', error);
                    this.$message.error('获取留言失败');
                });
        },
        formatLeaveTime(leaveTime) {
            const date = new Date(leaveTime);
            const options = { year: 'numeric', month: 'long', day: 'numeric', hour: '2-digit', minute: '2-digit', second: '2-digit' };
            return date.toLocaleString(undefined, options);
        }
    }
}
</script>

<style>
.lmessage-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    margin: 3rem;
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.lmessage-card {
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;

}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>