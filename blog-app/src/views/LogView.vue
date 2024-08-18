<template>
    <div>
        <my-nav></my-nav>
        <div class="log-list">
        <main>
            <article v-for="log in logs" :key="log.logId">
            <div class="log-card">
                <header>
                    <p class="meta">{{ log.time }}</p>
                </header>
                <section>
                    <p>{{ log.content }}</p>
                </section>
            </div>
          </article>
        </main>
    </div>
    </div>
</template>

<script>
export default {
    name: 'MyLogList',
    data() {
        return {
            logs: []
        }
    },
    async created() {
        await this.fetchLogs();
    },
    methods: {
        async fetchLogs() {
            this.$http.get('/log/all')
                .then(response => {
                    this.logs = response.data.data;
                })
                .catch(error => {
                    console.error('获取日志失败:', error);
                    this.$message.error('获取日志失败');
                });
        }
    }
}
</script>

<style>
.log-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    margin:3rem;
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.log-card {
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    
}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>