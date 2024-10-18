from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework import status, viewsets
from .models import CustomUser
from .serializers import CustomUserSerializer
from django.contrib.auth import authenticate, login, logout
from django.views.decorators.csrf import csrf_exempt
from rest_framework.permissions import AllowAny, IsAuthenticated
from rest_framework.parsers import MultiPartParser, FormParser
from rest_framework.authentication import SessionAuthentication, BasicAuthentication

class CustomUserViewSet(viewsets.ModelViewSet):
    queryset = CustomUser.objects.all()
    serializer_class = CustomUserSerializer
    permission_classes = [AllowAny]
    parser_classes = [MultiPartParser, FormParser]  # Add parsers for file uploads
    authentication_classes = [SessionAuthentication, BasicAuthentication]

    # Fetch the current user
    @action(detail=False, methods=['post'], permission_classes=[IsAuthenticated])
    def current_user(self, request):
        user = request.user
        print(user)
        serializer = CustomUserSerializer(user)
        return Response(serializer.data, status=status.HTTP_200_OK)

    # User signup
    @csrf_exempt
    @action(detail=False, methods=['post'], permission_classes=[AllowAny])
    def signup(self, request):
        serializer = CustomUserSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    # User login
    @csrf_exempt
    @action(detail=False, methods=['post'], permission_classes=[AllowAny])
    def login(self, request):
        username = request.data.get('username')
        password = request.data.get('password')
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            return Response({'message': 'Login successful'}, status=status.HTTP_200_OK)
        return Response({'error': 'Invalid credentials'}, status=status.HTTP_400_BAD_REQUEST)

    # User logout (Signout)
    @csrf_exempt
    @action(detail=False, methods=['post'], permission_classes=[IsAuthenticated])
    def logout(self, request):
        logout(request)  # Call Django's logout function
        return Response({'message': 'Logout successful'}, status=status.HTTP_200_OK)
