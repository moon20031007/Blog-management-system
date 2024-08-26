<template>
    <div class="article-list">
        <article v-for="article in articles" :key="article.id" @click="viewArticle(article.articleId)" class="article-card">
            <header>
                <h2 class="clickable-title">{{ article.title }}</h2>
                <p class="meta">By {{ users[article.authorId] }} on {{ $formatTime(article.publishTime) }}</p>
            </header>
            <section>
                <span><img :src="require('@/assets/images/view.png')" class="view-image">{{ article.readCount }}</span>&nbsp;
                <span><i class="el-icon-chat-dot-round">{{ article.commentCount }}</i></span>&nbsp;
                <span><img :src="require('@/assets/images/like.png')" class="like-image2">{{ article.likeCount }}</span>
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
.like-image2 {
    cursor: pointer;
    width: 1.8%; /* 调整宽度 */
    height: 1.8%; /* 调整高度 */
    margin-right: -1px; /* 调整与右边文本的间距 */
    margin-bottom: 4px; /* 向下移动图片 */
    vertical-align: middle; /* 确保图片与文本垂直对齐 */
}
.view-image {
    cursor: pointer;
    width: 1.9%; /* 调整宽度 */
    height: 1.9%; /* 调整高度 */
    margin-right: -2px; /* 调整与右边文本的间距 */
    margin-bottom: 2px; /* 向下移动图片 */
    vertical-align: middle; /* 确保图片与文本垂直对齐 */
}
</style>