git checkout --orphan codereview
git rm -rf .
git commit --allow-empty -m "Creating review branch"
git push --set-upstream origin codereview
git checkout -b baseline
git merge main --allow-unrelated-histories
git push --set-upstream origin baseline