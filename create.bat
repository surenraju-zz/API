kubectl apply -f api-namespace-v1.yaml
TIMEOUT /T 5
kubectl apply -f api-secret-v1.yaml --namespace=titanicapi
TIMEOUT /T 5
kubectl apply -f mysql-pv-v1.yaml --namespace=titanicapi
TIMEOUT /T 5
kubectl apply -f mysql-deployment-v1.yaml --namespace=titanicapi
TIMEOUT /T 60
kubectl apply -f api-init-job-v1.yaml --namespace=titanicapi
TIMEOUT /T 5
kubectl apply -f api-deployment-v1.yaml --namespace=titanicapi
TIMEOUT /T 5
kubectl apply -f api-ingress-v1.yaml --namespace=titanicapi
TIMEOUT /T 60