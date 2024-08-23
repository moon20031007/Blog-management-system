<template>
    <div class="article-list">
        <article v-for="article in articles" :key="article.id" @click="viewArticle(article.articleId)" class="article-card">
            <header>
                <h2 class="clickable-title">{{ article.title }}</h2>
                <p class="meta">By {{ users[article.authorId] }} on {{ $formatTime(article.publishTime) }}</p>
            </header>
            <section>
                <span><i class="el-icon-view">{{ article.readCount }}</i></span>&nbsp;
                <span><i class="el-icon-chat-dot-round">{{ article.commentCount }}</i></span>&nbsp;
                <span><i class="el-icon-star-off">{{ article.likeCount }}</i></span>
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
        },
        users: {
            type: Object,
            required: true
        }
    },
    methods: {
        viewArticle(id) {
            this.$router.push({ name: 'article', params: { id } });
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
    background-color: #f8f9fa;
    padding: 1.5rem;
    border-radius: 1rem;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    border: 1px solid #e0e0e0;
    transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.article-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

article header {
    margin-bottom: 0.5rem;
}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>