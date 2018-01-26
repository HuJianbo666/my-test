## bio

bio即传统io，堵塞io，一个线程进行IO操作完才会通知另外的IO操作线程，必须等待

主要为Reader、Writer、InputStream、OutputStream、RandomAccessFile、File这些类

详细见这个博客：http://blog.csdn.net/qq_25184739/article/details/51205186

## nio

非堵塞io

核心类：Channels、Buffers、Selectors

