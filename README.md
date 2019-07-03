# 카카오뱅크 이미지검색

## 구현 기능
* 이미지 검색 API, 동영상 검색 API 통합 검색 기능
* 검색 결과 썸네일로 표현
* 검색 결과 날짜 최신순으로 정렬
* 페이징 기능 구현(추가 로드된 데이터 끼리만 최신순으로 정렬)
* 검색한 결과 보관함에 저장할 수 있는 기능
* 보관함에 있는 이미지 삭제 기능


## 사용 기술 및 라이브러리
* [DataBinding][databinding]
* [AAC ViewModel][viewmodel]
* [AAC LiveData][livedata]
* [AAC Paging][paging]
* [Retrofit][retrofit] + [RxJava][rxjava]
* [Glide][glide]


[databinding]: https://developer.android.com/topic/libraries/data-binding
[viewmodel]: https://developer.android.com/topic/libraries/architecture/viewmodel
[livedata]: https://developer.android.com/topic/libraries/architecture/livedata
[paging]: https://developer.android.com/topic/libraries/architecture/paging
[retrofit]: http://square.github.io/retrofit
[rxjava]: https://github.com/ReactiveX/RxJava
[glide]: https://github.com/bumptech/glide
