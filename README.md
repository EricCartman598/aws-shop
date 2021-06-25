# aws-shop
Internet shop using AWS

To create and push docker image:
1. Create docker image:
sudo docker build -t aws-shop:&lt;version&gt; .

2. Rename docker image:
sudo docker tag aws-shop:&lt;version&gt; ericcartman598/aws-shop:&lt;version&gt;

3. Push the image to Docker Hub
sudo docker push ericcartman598/aws-shop:&lt;version&gt;

To redeploy the same version on cluster: 
kubectl rollout restart deployment/aws-shop-deployment

To set up a cluster and run your app there:
1. Create a cluster:
eksctl create cluster -f cluster.yaml

2. Add your credentials to access to your private repo on Docker Hub:
kubectl create secret docker-registry regcred --docker-server=https://index.docker.io/v2/ --docker-username=&lt;your 
dockerhub username&gt; --docker-password=&lt;your dockerhub password&gt; --docker-email=&lt;your email linked to your dockerhub account&gt;

3. Create a deployment:
kubectl apply -f deployment.yaml

4. OPTIONAL: You can Сheck how you pods work by creatmg port-forwarding and access to a pod:
kubectl port-forward &lt;your pod name&gt; 1234:8080
to get pods list run the command "kubectl get pods"

5. Create service of LoadBalancer type
kubectl apply -f service.yaml

To install docker:
sudo yum install docker -y
sudo service docker start
sudo docker login 
sudo docker run -d -p1234:8080 ericcartman598/aws-shop:latest