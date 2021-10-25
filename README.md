# Swagger

# Git

## HTTPS/SSH

HTTPS：[https://github.com/chenzufeng2021/BackendDevelop.git](https://github.com/chenzufeng2021/BackendDevelop.git)

SSH：[git@github.com:chenzufeng2021/BackendDevelop.git](git@github.com:chenzufeng2021/BackendDevelop.git)

## Git 全局设置

```markdown
git config --global user.name "Chenzufeng"
git config --global user.email "chenzufeng@outlook.com"
```

## 创建 git 仓库

```markdown
echo "# BackendDevelop" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin git@github.com:chenzufeng2021/BackendDevelop.git
git push -u origin main
```

## 已有仓库

```markdown
git remote add origin git@github.com:chenzufeng2021/BackendDevelop.git
git branch -M main
git push -u origin main
```

