<template>
    <div class="lmessage-list">
        <p>已有留言共{{ lmessages.length }}条</p>
        <main>
            <article v-for="lmessage in lmessages" :key="lmessage.lmessageId">
                <div class="lmessage-card">
                    <header>
                        <p class="meta">By 用户{{ lmessage.commenterId }} on {{ $formatTime(lmessage.leaveTime) }}</p>
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
            this.$http.get('/lmessage/list')
                .then(response => {
                    this.lmessages = response.data.data;
                })
                .catch(error => {
                    this.$message.error('获取留言失败：' + error);
                });
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