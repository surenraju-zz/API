kubectl delete -f api-secret-v1.yaml --namespace=titanicapi
kubectl delete -f mysql-pv-v1.yaml --namespace=titanicapi
kubectl delete -f mysql-deployment-v1.yaml --namespace=titanicapi
kubectl delete -f api-deployment-v1.yaml --namespace=titanicapi
kubectl delete -f api-ingress-v1.yaml --namespace=titanicapi
kubectl delete -f api-init-job-v1.yaml --namespace=titanicapi
kubectl delete -f api-namespace-v1.yaml