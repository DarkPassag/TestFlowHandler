package domain.repository

interface PostsRepository: UpdatePost, PatchPost, NewPost, FetchPostsById {
}