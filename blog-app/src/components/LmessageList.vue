<template>
    <div class="lmessage-list">
        <p>已有留言共{{ lmessages.length }}条</p>
        <main :key="lmessageKey">
            <article v-for="lmessage in lmessages" :key="lmessage.lmessageId">
                <div class="lmessage-card">
                    <header>
                        <p class="meta">By {{ users[lmessage.commenterId] }} on {{ $formatTime(lmessage.leaveTime) }}
                        </p>
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
            lmessages: [],
            users: {},
            lmessageKey: 0
        }
    },
    async created() {
        await this.fetchLmessages();
    },
    methods: {
        async fetchLmessages() {
            this.$http.get('/lmessage/list')
                .then(response => {
                    if (response.data.code == 0) {
                        this.lmessages = response.data.data;
                        this.lmessages.forEach(lmessage => {
                            if (!this.users[lmessage.commenterId]) {
                                this.users[lmessage.commenterId] = '';
                            }
                        });
                    } else {
                        this.$message.error('获取留言失败：' + response.data.msg);
                    }
                    this.fetchNames(Object.keys(this.users));
                })
                .catch(error => {
                    this.$message.error('获取留言失败：' + error);
                });
        },
        async fetchNames(keys) {
            let userIdsParam = keys.length == 1 ? keys[0] : keys.join(',');
            this.$http.get(`/user/names`, { params: { userIds: userIdsParam } })
                .then(response => {
                    if (response.data.code == 0) {
                        response.data.data.forEach(user => this.users[user.accountId.toString()] = user.nickname);
                        this.lmessageKey++;
                    } else {
                        this.$message.error('获取用户名失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取用户名失败：' + error);
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