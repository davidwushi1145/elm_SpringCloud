#!/bin/bash

# 重命名com文件夹为cn
find . -type d -name 'com' | while read dir; do
    mv "$dir" "${dir%/*}/cn"
done

# 重命名neusoft文件夹为wushi
find . -type d -name 'neusoft' | while read dir; do
    mv "$dir" "${dir%/*}/wushi"
done

# 重命名后的文件夹中的文件路径需要更新
find . -type f -name '*.java' -o -name '*.yml' -o -name '*.xml' -o -name '*.properties' | while read file; do
    sed -i 's/com\./cn\./g' "$file"
    sed -i 's/neusoft\./wushi\./g' "$file"
done

# 重命名编译后的class文件路径
find . -type f -name '*.class' | while read file; do
    new_file=$(echo "$file" | sed 's/com\//cn\//g' | sed 's/neusoft\//wushi\//g')
    mkdir -p "$(dirname "$new_file")"
    mv "$file" "$new_file"
done
