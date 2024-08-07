<template>
    <div>
        <aside class="sidebar">
            <section class="sidebar-section">
                <h3>最热标签</h3>
                <ul class="tags-list">
                    <li v-for="tag in hotTags" :key="tag">
                        <a :href="'/tag/' + tag.tagId">{{ tag.tagName }}</a>
                    </li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3>最热文章</h3>
                <ul class="articles-list">
                    <li v-for="article in hotArticles" :key="article.articleId">
                        <a :href="'/article/' + article.articleId">{{ article.title }}</a>
                    </li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3>最新文章</h3>
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
import axios from 'axios';

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
            axios.get('http://localhost:7000/tag/hot')
                .then(response => {
                        this.hotTags = response.data.data;
                    })
                .catch(error => {
                    console.error('获取最热标签失败:', error);
                    this.$message.error('获取最热标签失败');
                });
        },
        async fetchHotArticles() {
            axios.get('http://localhost:7000/article/hot')
                .then(response => {
                        this.hotArticles = response.data.data;
                    })
                .catch(error => {
                    console.error('获取最热文章失败:', error);
                    this.$message.error('获取最热文章失败');
                });
        },
        async fetchLatestArticles() {
            axios.get('http://localhost:7000/article/latest')
                .then(response => {
                        this.latestArticles = response.data.data;
                    })
                .catch(error => {
                    console.error('获取最新文章失败:', error);
                    this.$message.error('获取最新文章失败');
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

.articles-list a {
    color: #007BFF;
    text-decoration: none;
    cursor: pointer;
}

.articles-list a:hover {
    text-decoration: underline;
}
</style>