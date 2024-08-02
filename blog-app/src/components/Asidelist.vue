<template>
    <div>
        <aside class="sidebar">
            <section class="sidebar-section">
                <h3>最热标签</h3>
                <ul class="tags-list">
                    <li v-for="tag in hotTags" :key="tag">{{ tag }}</li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3>最热文章</h3>
                <ul class="articles-list">
                    <li v-for="article in hotArticles" :key="article.id">
                        <a @click.prevent="viewArticle(article.id)">{{ article.title }}</a>
                    </li>
                </ul>
            </section>

            <section class="sidebar-section">
                <h3>最新文章</h3>
                <ul class="articles-list">
                    <li v-for="article in latestArticles" :key="article.id">
                        <a @click.prevent="viewArticle(article.id)">{{ article.title }}</a>
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
            hotTags: ['Vue.js', 'JavaScript', 'Web Development', 'Frontend', 'CSS'],
            hotArticles: [
                { id: 1, title: 'Understanding Vue.js Directives' },
                { id: 2, title: 'JavaScript ES6 Features' },
                // 更多最热文章数据
            ],
            latestArticles: [
                { id: 3, title: 'New Features in Vue 3' },
                { id: 4, title: 'How to Use Vue Router' },
                // 更多最新文章数据
            ]
        }
    },
    methods: {
        viewArticle(id) {
            // Navigate to the article detail page
            this.$router.push({ name: 'ArticleDetail', params: { id } });
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