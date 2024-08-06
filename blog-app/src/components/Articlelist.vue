<template>
    <div class="article-list">
        <article v-for="article in articles" :key="article.id" class="article-card">
            <header>
                <h2 @click="viewArticle(article.id)" class="clickable-title">{{ article.title }}</h2>
                <p class="meta">By {{ article.author }} on {{ article.date }}</p>
            </header>
            <section>
                <span><i class="el-icon-view">{{ article.read_count }}</i></span>
                <span><i class="el-icon-chat-dot-round">{{ article.comment_count }}</i></span>
                <span><i class="el-icon-star-off">{{ article.like_count }}</i></span>
            </section>
        </article>
    </div>
</template>

<script>
export default {
    name: 'MyArticleList',
    props: {
        articles: {
            type: Array,
            required: true
        }
    },
    methods: {
        viewArticle(id) {
            this.$router.push({ path: '/article', params: { id } });
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