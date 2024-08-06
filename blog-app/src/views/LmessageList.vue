<template>
    <div class="lmessage-list" >
        <p>已有留言共{{ articles.length }}条</p>
        <main >
            <article v-for="article in articles" :key="article.id" >
            <div class="lmessage-card">
                <header>
                    <p class="meta">By {{ article.author }} on {{ article.date }}</p>
                </header>
                <section>
                    <p>{{ article.excerpt }}</p>
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
            articles: [
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 2, title: 'Article 2', author: 'Author 2', date: '2024-08-02', excerpt: 'This is the excerpt for article 2.' },
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
                { id: 1, title: 'Article 1', author: 'Author 1', date: '2024-08-01', excerpt: 'This is the excerpt for article 1.' },
            
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
.lmessage-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    margin-top:3rem;
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