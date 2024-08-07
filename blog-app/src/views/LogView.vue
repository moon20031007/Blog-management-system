<template>
    <div>
        <my-nav></my-nav>
        <div class="log-list" >
        <main >
            <article v-for="article in articles" :key="article.id" >
            <div class="log-card">
                <header>
                    <p class="meta">{{ article.time }}</p>
                </header>
                <section>
                    <p>{{ article.content }}</p>
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
            articles: [
                { id: 1, content: 'This is the excerpt for article 1.', time: '2024-08-01'},
                { id: 1, content: 'This is the excerpt for article 1.', time: '2024-08-01'},
                { id: 1, content: 'This is the excerpt for article 1.', time: '2024-08-01'},
                { id: 1, content: 'This is the excerpt for article 1.', time: '2024-08-01'}
                // 更多文章数据
            ]
        }
    },
    methods: {
    
    }
}
</script>

<style>
.log-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
    margin:3rem;
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.log-card {
    border: 1px solid #ddd;
    padding: 1rem;
    border-radius: 0.5rem;
    
}

article .meta {
    font-size: 0.9em;
    color: #666;
}
</style>