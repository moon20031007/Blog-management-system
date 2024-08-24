<template>
    <div>
        <aside class="sidebar">
            <section class="sidebar-section">
                <h3 class="h3-1">最热标签</h3>
                <ul class="tags-list">
                    <li v-for="tag in hotTags" :key="tag">
                        <a :href="'/tag/' + tag.tagId">{{ tag.tagName }}</a>
                    </li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3 class="h3-1">最热文章</h3>
                <ul class="articles-list">
                    <li v-for="article in hotArticles" :key="article.articleId">
                        <a :href="'/article/' + article.articleId">{{ article.title }}</a>
                    </li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3 class="h3-1">最新文章</h3>
                <ul class="articles-list">
                    <li v-for="article in latestArticles" :key="article.articleId">
                        <a :href="'/article/' + article.articleId">{{ article.title }}</a>
                    </li>
                </ul>
            </section>
        </aside>
    </div>
</template>

<script>
export default {
    name: 'MyAsideList',
    data() {
        return {
            hotTags: [],
            hotArticles: [],
            latestArticles: []
        }
    },
    async created() {
        await this.fetchHotTags();
        await this.fetchHotArticles();
        await this.fetchLatestArticles();
    },
    methods: {
        async fetchHotTags() {
            this.$http.get('/tag/hot')
                .then(response => {
                    if (response.data.code == 0) {
                        this.hotTags = response.data.data;
                    } else {
                        this.$message.error('获取最热标签失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取最热标签失败：' + error);
                });
        },
        async fetchHotArticles() {
            this.$http.get('/article/hot')
                .then(response => {
                    if (response.data.code == 0) {
                        this.hotArticles = response.data.data;
                    } else {
                        this.$message.error('获取最热文章失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取最热文章失败：' + error);
                });
        },
        async fetchLatestArticles() {
            this.$http.get('/article/latest')
                .then(response => {
                    if (response.data.code == 0) {
                        this.latestArticles = response.data.data;
                    } else {
                        this.$message.error('获取最新文章失败：' + response.data.msg);
                    }
                })
                .catch(error => {
                    this.$message.error('获取最新文章失败：' + error);
                });
        }
    }
}
</script>

<style>
.sidebar {
    width: 250px;
    padding: 1rem;
    background-color: #f9f9f9;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    position: fixed;
    top: 50%;
    left: 0;
    transform: translateY(-50%);
    z-index: 1000; /* 确保侧栏在其他元素上方 */
}

.sidebar-section {
    margin-bottom: 2rem;
}

.sidebar-section h3 {
    font-size: 1.2rem;
    margin-bottom: 0.5rem;
}

.tags-list,
.articles-list {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.tags-list li,
.articles-list li {
    margin-bottom: 0.5rem;
}

.tags-list a,
.articles-list a {
    color: black;
    text-decoration: none;
    cursor: pointer;
    margin-left: 10px;
}

.tags-list a:hover,
.articles-list a:hover {
    color: #5492e3;
}

.h3-1 {
    background-color: #75a6e5;
    color: white;
    padding: 0.5rem 1rem;
    border-radius: 0.25rem;
}
</style>