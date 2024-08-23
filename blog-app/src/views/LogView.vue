<template>
    <div>
        <my-nav></my-nav>
        <div class="log-list">
        <main>
            <article v-for="log in logs" :key="log.logId">
            <div class="log-card">
                <header>
                    <p class="meta">{{ $formatTime(log.time) }}</p>
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
                    if (response.data.code == 0) {
                        this.logs = response.data.data;
                    } else {
                        this.$message.error('获取日志失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取日志失败：' + error);
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
    
    padding: 1rem;
    border-radius: 0.5rem;
    
}

.log-card {
    background-color: #f8f9fa;
    padding: 1.5rem;
    border-radius: 1rem;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    border: 1px solid #e0e0e0;
    transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.log-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>