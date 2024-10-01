#!/bin/bash
# Đây là một shell script để thực hiện các tác vụ tùy chỉnh với các bước debug

# In ra vị trí hiện tại để kiểm tra xem đang ở đúng thư mục không
echo "Current working directory: $(pwd)"

# Liệt kê tất cả các file và thư mục hiện có để xem thư mục artifact_storage có tồn tại hay không
echo "Listing all files and directories in current path:"
ls -al

# 1. Tạo thư mục mới trong repository để lưu trữ JAR tại root directory
NEW_REPO_DIR="$GITHUB_WORKSPACE/repository_storage"
echo "Creating directory: $NEW_REPO_DIR in repository root"
mkdir -p $NEW_REPO_DIR

# 2. Tạo thư mục artifact_storage nếu chưa tồn tại
ARTIFACT_PATH="./artifact_storage/"  # Sử dụng đường dẫn tương đối trong thư mục hiện tại
if [ ! -d "$ARTIFACT_PATH" ]; then
  echo "Artifact directory not found. Creating $ARTIFACT_PATH"
  mkdir -p $ARTIFACT_PATH
fi

# 3. Kiểm tra xem file .jar có trong thư mục target hay không và di chuyển nó đến artifact_storage
echo "Checking for .jar files in target/..."
ls -al target/

echo "Moving build artifacts to $ARTIFACT_PATH..."
mv target/*.jar $ARTIFACT_PATH

# 4. In ra danh sách các file trong thư mục artifact để xác nhận
echo "Listing files in $ARTIFACT_PATH:"
ls -al $ARTIFACT_PATH

# 5. Copy file .jar từ artifact_storage sang thư mục repository mới tạo tại root
echo "Copying JAR file to $NEW_REPO_DIR..."
cp $ARTIFACT_PATH*.jar $NEW_REPO_DIR/

# 6. Kiểm tra và xác nhận file đã được copy thành công
echo "Listing files in $NEW_REPO_DIR:"
ls -al $NEW_REPO_DIR
