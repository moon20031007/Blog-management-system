<template>
    <div class="article-list">
        <article v-for="article in articles" :key="article.id" class="article-card">
            <header>
                <h2 @click="viewArticle(article.id)" class="clickable-title">{{ article.title }}</h2>
                <p class="meta">By {{ article.author }} on {{ article.date }}</p>
            </header>
            <section>
                <p>{{ article.excerpt }}</p>
            </section>
        </article>
    </div>
</template>

<script>
export default {
    name: 'MyArticleList',
    data() {
        return {
            articles: [
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 2, title: 'Article 2', author: 'Author 2', date: '2024-08-02', excerpt: 'This is the excerpt for article 2.' },
                // 更多文章数据
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
.article-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.article-card {
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

article header {
    margin-bottom: 0.5rem;
}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>